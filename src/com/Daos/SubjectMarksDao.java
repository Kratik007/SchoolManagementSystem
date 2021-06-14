/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.Entities.SubjectMarks;
/**
 *
 * @author DELL
 */
public class SubjectMarksDao {
    private static Connection con;

    static {
        con = com.connections.dbConnection.getConnection();
    }
    public SubjectMarksDao(){
    super();
    }
    public static ArrayList<String> getSubjectsName(String class_id,JFrame jf){
        try{
            PreparedStatement stmt= con.prepareStatement("select subject_name from subject where class_id=?");
            stmt.setString(1, class_id);
            ResultSet rs=stmt.executeQuery();
            ArrayList<String> lst=new ArrayList<>();
            while(rs.next()){
                lst.add(rs.getString("subject_name"));
            }
            return lst;
        }catch(SQLException s){
            s.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in connecting with database");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(jf,"Error in application try again");
        }
        return null;
    }
    public static boolean updateMarksStatus(int enrollment,String class_id,String subject_name,float marks_obtain,String exam_name,float out_of,JFrame jf){
     try{
         PreparedStatement ps=con.prepareStatement("insert into subject_marks values(?,?,?,?,?,?)");
         ps.setInt(1,enrollment);         
         ps.setString(2,class_id);
         ps.setString(3,subject_name);
         ps.setFloat(4,marks_obtain);
         ps.setString(5,exam_name);
         ps.setFloat(6,out_of);
         int i=ps.executeUpdate();
         if(i==1){
             return true;
         }
        }catch(SQLException s){
            s.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in connecting with database");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(jf,"Error in application try again");
        }
        return false;
    }
    public static HashMap<Integer,HashMap<String,Float>> getAllSubjectMarks(String class_id,String exam,JFrame jf){
        try{
            PreparedStatement ps=con.prepareStatement("select enrollment,subject_name,marks_obtain,out_of from subject_marks where enrollment in (select enrollment from student_in_class where class_id=?) and class_id=? and exam_name=?");
            ps.setString(1,class_id);
            ps.setString(2,class_id);
            ps.setString(3, exam);
            ResultSet rs=ps.executeQuery();
            HashMap<Integer,HashMap<String,Float>> hm=new HashMap<>();
            while(rs.next()){
                int enrollment=rs.getInt("enrollment");
                if(hm.containsKey(enrollment)){
                    HashMap<String,Float> inmp=hm.get(enrollment);
                    inmp.put(rs.getString("subject_name"),rs.getFloat("marks_obtain"));
                    hm.put(enrollment, inmp);
                }else{
                    HashMap<String,Float> inmp=new HashMap<>();
                    inmp.put(rs.getString("subject_name"),rs.getFloat("marks_obtain"));
                    hm.put(enrollment, inmp);
                }
            }
            System.out.println(hm);
            return hm;
        }catch(SQLException s){
            s.printStackTrace();
            JOptionPane.showMessageDialog(jf, "Error in connecting with database");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(jf,"Error in application try again");
        }
        return null;
    }
}
