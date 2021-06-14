package com.connections;
import java.io.IOException;
import java.sql.*;
import java.io.InputStream;
import java.util.Properties;
public class dbConnection
{
    public static Connection con=null;
          static dbConnection Connections=null;
    public void initialize()
   {
       Properties pro = new Properties();
		InputStream ins = getClass().getResourceAsStream(
			"Details.properties");
		try {
			pro.load(ins);
			String driver = pro.getProperty("driver");
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException sq) {
			System.out.println("Unable to Create Connection." + sq);
		} catch (ClassNotFoundException cn) {
			System.out.println("Unable to Load Driver Class.");
		} catch (IOException io) {
			System.out.println("Unable to Load Property File.");
		}
}

   static public Connection getConnection()
    {
        if(Connections==null)
        {
            Connections=new dbConnection();
            System.out.println("New");
            Connections.initialize();
            return con;
        } 
        else { 
        System.out.println("Old");
        return con;
        }
    }
   public static void main(String args[]){
//           dbConnection DbConnection=new dbConnection();
//           DbConnection.getConnection();
//           System.out.println("Connected");
   }
} 
