/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Daos;

import com.Entities.Fees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.Entities.Student;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class StudentDao {

    private static Connection con;

    static {
        con = com.connections.dbConnection.getConnection();
    }

    public StudentDao() {
        super();
    }

    public static int saveStudent(Student student, JFrame jf) {
        try {
//        java.sql.Date d=new java.sql.Date();
            PreparedStatement ps = StudentDao.con.prepareStatement("insert into student(name,dob,fname,mname,fmobile,foccupation,aadharno,sssmid,address,isstudying,Addmission_date) values(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, student.getName());
            ps.setDate(2, new java.sql.Date(student.getDob().getYear(), student.getDob().getMonth(), student.getDob().getDate()));
            ps.setString(3, student.getFname());
            ps.setString(4, student.getMname());
            ps.setString(5, student.getFmobile());
            ps.setString(6, student.getFoccupation());
            ps.setString(7, student.getAadhar());
            ps.setString(8, student.getSssmid());
            ps.setString(9, student.getAddress());
            ps.setBoolean(10, true);
            ps.setDate(11, student.getAddmission_date());
            int i = ps.executeUpdate();
            System.out.println(new java.sql.Date(student.getDob().getYear(), student.getDob().getMonth(), student.getDob().getDate()));
            if (i != 0) {
                JOptionPane.showMessageDialog(jf, "Student Record created succesfully!!");
                PreparedStatement enroll = StudentDao.con.prepareStatement("select enrollment from student where aadharno=?");
                enroll.setString(1, student.getAadhar());
                ResultSet s = enroll.executeQuery();
                s.next();
                return s.getInt("enrollment");
            } else {
                JOptionPane.showMessageDialog(jf, "Problem is creating record try again :(");
            }
        } catch (SQLException sqlw) {
            sqlw.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in Database");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in Applicaiton");
        }
        return 0;
    }
    public static boolean updateStudent(Student student,JFrame jf){
        try{
            PreparedStatement ps=StudentDao.con.prepareStatement("update student set name=?,dob=?,fname=?,mname=?,fmobile=?,foccupation=?,aadharno=?,sssmid=?,isstudying=?,tcdate=?,address=?,Addmission_date=? where enrollment=?");
            ps.setString(1, student.getName());
            ps.setDate(2, new java.sql.Date(student.getDob().getYear(),student.getDob().getMonth(),student.getDob().getDate()));
            ps.setString(3, student.getFname());
            ps.setString(4, student.getMname());
            ps.setString(5, student.getFmobile());
            ps.setString(6, student.getFoccupation());
            ps.setString(7, student.getAadhar());
            ps.setString(8, student.getSssmid());
            ps.setBoolean(9, student.isIsStudying());
            ps.setDate(10, new java.sql.Date(student.getTcDate().getYear(),student.getTcDate().getMonth(),student.getTcDate().getDate()));
            ps.setString(11, student.getAddress());
            ps.setDate(12,student.getAddmission_date());
            ps.setInt(13, student.getEnrollment());
            int i=ps.executeUpdate();
            if(i>0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException s){
            JOptionPane.showMessageDialog(jf,"Error in updating student");
            s.printStackTrace();
        }catch(Exception e){
            JOptionPane.showMessageDialog(jf,"Error in application");
        }
        return false;
    }
    public static int getEnrollmentByNameAndDobAndYear(String name, java.sql.Date dob, String class_id, JFrame jf) {
        try {
            PreparedStatement ps = StudentDao.con.prepareStatement("select s.enrollment from student s inner join student_in_class sc on s.enrollment=sc.enrollment where s.name=? and s.dob=? and sc.class_id=?");
            ps.setString(1, name);
            ps.setDate(2, dob);
            System.out.println(dob);
            ps.setString(3, class_id);
            ResultSet i = ps.executeQuery();
//        System.out.println(new java.sql.Date(student.getDob().getYear(),student.getDob().getMonth(),student.getDob().getDate()));
            while (i.next()) {
                return i.getInt("enrollment");
            }
        } catch (SQLException sqlw) {
            sqlw.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in Database");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in Applicaiton");
        }
        return 0;
    }
    public static Student getStudentFromEnrollment(int enrollment,JFrame jf){
        try {
            PreparedStatement ps = StudentDao.con.prepareStatement("select * from student where enrollment=?");
            ps.setInt(1, enrollment);
//            ps.setDate(2, dob);
//            System.out.println(dob);
//            ps.setString(3, class_id);
            ResultSet i = ps.executeQuery();
//        System.out.println(new java.sql.Date(student.getDob().getYear(),student.getDob().getMonth(),student.getDob().getDate()));
            while (i.next()) {
//                System.out.println();
                Student student=new Student(i.getInt("enrollment"), i.getString("name"), new java.util.Date(i.getDate("dob").getYear()+"/"+i.getDate("dob").getMonth()+"/"+i.getDate("dob").getDate()), i.getString("fname"), i.getString("mname"), i.getString("fmobile"), i.getString("foccupation"), i.getString("aadharno"), i.getString("sssmid"), i.getBoolean("isstudying"),new java.util.Date(i.getDate("tcdate").getYear()+"/"+i.getDate("tcdate").getMonth()+"/"+i.getDate("tcdate").getDate()), i.getString("address"),i.getDate("Addmission_date"));
                return student;
            }
        } catch (SQLException sqlw) {
            sqlw.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in Database");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in Applicaiton");
        }
        return null;
    }
    public static double[] getTotalPayableFee(String class_id, JFrame jf) {
        try {
            double[] d = new double[3];
            PreparedStatement ps = con.prepareStatement("select installment1,installment2,installment3 from class where class_id=?");
            ps.setString(1, class_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                d[0] = rs.getDouble("installment1");
                d[1] = rs.getDouble("installment2");
                d[2] = rs.getDouble("installment3");
            }
            return d;
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(jf, "Error in fetching details");
            s.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Fees getFeesStatus(int enrollment, String class_id, JFrame jf) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from fees where enrollment=? and class_id=?");
            ps.setInt(1, enrollment);
            ps.setString(2, class_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fees fee = new Fees(rs.getInt("enrollment"), rs.getString("class_id"), rs.getDouble("installment1"), rs.getString("receipt_no1"), rs.getDouble("installment2"), rs.getString("receipt_no2"), rs.getDouble("installment3"), rs.getString("receipt_no3"), rs.getBoolean("clear_status"));
                System.out.println(fee);
                return fee;
            }
        } catch (SQLException s) {
            s.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error while fetching details from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(jf, "No fee record found");
        return null;
    }

    public static boolean UpdateStatus(int enrollment, String class_id, int installment_no, double amount, String receipt_no, boolean is_clear, JFrame jf) {
        try {
            String sql = "";
            PreparedStatement temp = con.prepareStatement("select enrollment from fees where enrollment=?");
            temp.setInt(1, enrollment);
            ResultSet r = temp.executeQuery();
            if (!r.next()) {
                switch (installment_no) {
                    case 1:
                        sql = "insert into fees(enrollment,class_id,installment1,receipt_no1,clear_status) values(?,?,?,?,?)";
                        break;
                    case 2:
                        sql = "insert into fees(enrollment,class_id,installment2,receipt_no2,clear_status) values(?,?,?,?,?)";
                        break;
                    case 3:
                        sql = "insert into fees(enrollment,class_id,installment3,receipt_no3,clear_status) values(?,?,?,?,?)";
                        break;
                }

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDouble(3, amount);
                ps.setString(4, receipt_no);
                ps.setBoolean(5, is_clear);
                ps.setInt(1, enrollment);
                ps.setString(2, class_id);
                int rs = ps.executeUpdate();
                if (rs == 1) {
                    return true;
                }
            } else {
                switch (installment_no) {
                    case 1:
                        sql = "update fees set installment1=?,receipt_no1=?,clear_status=? where enrollment=? and class_id=?";
                        break;
                    case 2:
                        sql = "update fees set installment2=?,receipt_no2=?,clear_status=? where enrollment=? and class_id=?";
                        break;
                    case 3:
                        sql = "update fees set installment3=?,receipt_no3=?,clear_status=? where enrollment=? and class_id=?";
                        break;
                }

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, receipt_no);
                ps.setBoolean(3, is_clear);
                ps.setInt(4, enrollment);
                ps.setString(5, class_id);
                int rs = ps.executeUpdate();
                if (rs == 1) {
                    return true;
                }
            }
        } catch (SQLException s) {
            s.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error while fetching details from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
        return false;
    }
//    public static HashMap<> getAllStudentsMarks(){
//        
//    }
}
