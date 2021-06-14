/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Daos;

import com.Entities.Staff;
import com.connections.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.Entities.Student;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.util.Date;
import javax.swing.JFrame;
/**
 *
 * @author DELL
 */
public class ClassDao {
    public static ArrayList<String> getAllClass(){
        Connection con=dbConnection.getConnection();
            ArrayList<String> list=new ArrayList<>();
         try{
            PreparedStatement stmt=con.prepareStatement("select class_id from class");
                
                ResultSet rs=stmt.executeQuery();
                
                while(rs.next()){
                    list.add(rs.getString("class_id"));
                }
            }catch(SQLException s){
                s.printStackTrace();
            }
//            System.out.println(list);
            return list;
    }   
    public static ArrayList<Student> getAllStudentFromClass(String class_id,String year){
        Connection con=dbConnection.getConnection();
            ArrayList<Student> list=new ArrayList<>();
         try{
             java.util.Date date=new java.util.Date();
             System.out.println(year+5);
             System.out.println(Integer.parseInt(year)+5);
             date.setYear(Integer.parseInt(year));
            PreparedStatement stmt=con.prepareStatement("select s.enrollment,name,dob,fname,mname,fmobile,foccupation,aadharno,sssmid,isstudying,tcdate,address,Addmission_date from student s inner join student_in_class c where s.enrollment=c.enrollment and c.class_id=? and year(c.year)=?");
               stmt.setString(1,class_id);
               stmt.setString(2,year);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    Student student=new Student(rs.getInt("enrollment"), rs.getString("name"), rs.getDate("dob"), rs.getString("fname"), rs.getString("mname"), rs.getString("fmobile"), rs.getString("foccupation"), rs.getString("aadharno"), rs.getString("sssmid"), rs.getBoolean("isStudying"), rs.getDate("tcdate"), rs.getString("address"), rs.getDate("addmission_date"));
                    list.add(student);
                }
            }catch(SQLException s){
                s.printStackTrace();
            }
//         System.out.println(list.get(0));
         return list;
    }
    public static boolean setStudentClass(int enrollment,String class_id){
        Connection con=dbConnection.getConnection();
        try{
            PreparedStatement stmt=con.prepareStatement("insert into student_in_class values(?,?,?)");
               stmt.setInt(2,enrollment);
               stmt.setString(1,class_id);
               stmt.setDate(3,new java.sql.Date(new java.util.Date().getYear(),new java.util.Date().getMonth(),new java.util.Date().getDate()));
                int rs=stmt.executeUpdate();
                if(rs>0){
                    return true;
                }
            }catch(SQLException s){
                s.printStackTrace();
            }
        return false;
    }
    public static String getClassIdFromClassTeacher(int staff_id,JFrame jf){
        try{
            PreparedStatement stmt=dbConnection.getConnection().prepareStatement("select class_id from class where staffid=?");
            stmt.setInt(1,staff_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                return rs.getString("class_id");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(jf,"Error in connection try again");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(jf,"Error in application try again");
        }
        return null;
    }
    public static int getClassTeacher(String class_id,JFrame jf){
        try{
            PreparedStatement stmt=dbConnection.getConnection().prepareStatement("select staffid from class where class_id=?");
            stmt.setString(1,class_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                return rs.getInt("staffid");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(jf,"Error in connecting try again");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(jf,"Error in application try again");
        }
        return 0;
    }
    public static boolean setClassTeacher(String class_id,int staffid,JFrame jf){
        try{
            PreparedStatement stmt=dbConnection.getConnection().prepareStatement("update class set staffid=? where class_id=?");
            stmt.setInt(1,staffid);
            stmt.setString(2,class_id);
            int rs=stmt.executeUpdate();
            if(rs==1){
                return true;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(jf,"Error in connecting try again");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(jf,"Error in application try again");
        }
        return false;
    }
}
