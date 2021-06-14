/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Daos;
import com.Entities.Admin;
import com.connections.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class AdminDao {
    public static Admin isAdminValid(String username,String password,JFrame obj){
        
        Connection con=dbConnection.getConnection();
            try{
            PreparedStatement stmt=con.prepareStatement("select * from admin where username=? and password=?");
            stmt.setString(1,username);
            stmt.setString(2, password);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(obj,"Welcome!! "+rs.getString("name"));
                    Admin admin=new Admin(rs.getString("username"),rs.getString("name"),""+rs.getCharacterStream("mobile"));
                    
                    return admin;
                }else{
                    JOptionPane.showMessageDialog(obj,"Invalid Credentials");
                    return null;
                }
            }catch(SQLException s){
                JOptionPane.showMessageDialog(obj, "Error in connecting with database");
            }
            return null;
    }
}
