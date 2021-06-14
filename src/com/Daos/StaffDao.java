
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class StaffDao {
    public static Staff isStaffValid(int staffid,String password,JFrame obj){
         Connection con=dbConnection.getConnection();
            try{
            PreparedStatement stmt=con.prepareStatement("select * from staff where staffid=? and password=?");
            stmt.setInt(1,staffid);
            stmt.setString(2, password);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(obj, "Welcome!! "+rs.getString("name"));
                    
                    Staff stf=new Staff(rs.getInt("staffid"),rs.getString("fname_or_hname"),rs.getDate("dob"),rs.getString("qualification"),""+rs.getCharacterStream("mobile"),rs.getString("address"),rs.getString("name"));
                    System.out.println(stf);
                    return stf;
                }else{
                    JOptionPane.showMessageDialog(obj,"Invalid Credentials");
                }
            }catch(SQLException s){
                JOptionPane.showMessageDialog(obj, "Error in connecting with database");
            }
            return null;
    }
    public static boolean saveStaffDetails(Staff staff,String password,JFrame obj){
        Connection con=dbConnection.getConnection();
        try{
            PreparedStatement ps=con.prepareStatement("insert into staff(fname_or_hname,dob,qualification,mobile,address,password,name) value(?,?,?,?,?,?,?)");
            ps.setString(1, staff.getFname_hname());
            ps.setDate(2, staff.getSQLDob());
            ps.setString(3,staff.getQualification());
            ps.setString(4,staff.getMobile());
            ps.setString(5,staff.getAddress());
            ps.setString(6,password);
            ps.setString(7,staff.getName());
            int bool=ps.executeUpdate();
            if(bool>0){
                return true;
            }
        }catch(SQLException s){
            JOptionPane.showMessageDialog(obj, "Database Error try again");
            s.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(obj,"Internal Error");
        }
        return false;
    }
    public static ArrayList<Staff> getAllStaff(JFrame obj){
        Connection con=dbConnection.getConnection();
            try{
                ArrayList<Staff> stfarr=new ArrayList();
                PreparedStatement stmt=con.prepareStatement("select staffid,name from staff");
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    Staff stf=new Staff(rs.getInt("staffid"),null,null,null,null,null,rs.getString("name"));
                    stfarr.add(stf);
                }
                return stfarr;
                
            }catch(SQLException s){
                JOptionPane.showMessageDialog(obj, "Error in connecting with database");
            }
            return null;
    }
    public static String getStaffNameFromStaffId(int staffid,JFrame obj){
        Connection con=dbConnection.getConnection();
        try{
            PreparedStatement stmt=con.prepareStatement("select name from staff where staffid=?");
            stmt.setInt(1,staffid);
            ResultSet i=stmt.executeQuery();
           
            if( i.next()){
                return i.getString("name");
            }
        }catch(SQLException s){
            JOptionPane.showMessageDialog(obj,"Error in connecting with database try again");
        }catch(Exception e){
            JOptionPane.showMessageDialog(obj,"Error in application try again");
        }return null;
        
    }
}
