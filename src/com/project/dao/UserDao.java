/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.dao;

import com.project.db.*;
import com.project.view.Register;
import com.project.bean.UserBean;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UserDao {
    
    PreparedStatement st;
    ResultSet rs;
    Boolean resultStatus=Boolean.FALSE;
    Connection con=DBConnect.getConnection();
    
    public boolean userRegistration(UserBean bean)
	   {
		try
		   {
		    String sql="Select * from tbluser where user_email=?";
				st = con.prepareStatement(sql);
				st.setString(1,bean.getEmail());
				rs=st.executeQuery();
				Boolean b=rs.next();
				
				if(b==true)
				{
				System.out.println("Record already exist");
				}
				
				else
				{
				
				String SQL="insert into tbluser(user_name, user_address, user_email, user_mob, user_password) values(?,?,?,?,?)"; 
				
					PreparedStatement pstmt=con.prepareStatement(SQL);
					pstmt.setString(1, bean.getUname());
					pstmt.setString(2, bean.getAddress());
					pstmt.setString(3, bean.getEmail());
					pstmt.setString(4, bean.getMobNo());
					pstmt.setString(5, bean.getPassword());
					
					
					
					
					
					
					
					/*Part part=request.getPart("file");

					if(part!=null)
					{
					image=part.getInputStream();
					System.out.println("is size:"+image.available());
					imageName=db.extractFileName(part);
					System.out.println("name:"+imageName);
					}
					*/
					
					int index=pstmt.executeUpdate();
					
					if(index>0)
					{
						resultStatus=Boolean.TRUE;
					}
					else
					{
						resultStatus=Boolean.FALSE;	
					}
					
			   
	     	    }
		   }
				
				catch (SQLException e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
					
		     return resultStatus;
				
		 }
	
   
     
    public  boolean loginCheck(UserBean bean)
     {
		
    	try {
			String sql="Select * from tbluser where user_email=? and user_password=?";
			st = con.prepareStatement(sql);
			st.setString(1,bean.getEmail());
			st.setString(2,bean.getPassword());
			ResultSet rs=st.executeQuery();
			resultStatus=rs.next();
		} 
		catch (SQLException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	
		return resultStatus;
		 	
		 
	}
    public UserBean userSelect(String email)
   	{
       	UserBean bean=new UserBean();
   		String sql = "Select * from tbluser where user_email ='"+email.toLowerCase()+"'";
   		
   		try {
   			
   			Statement stmt=con.createStatement();
   		
   			ResultSet rs = stmt.executeQuery(sql);
   			if(rs.next())
   			{
   			    bean.setUname(rs.getString(2));
   			    bean.setEmail(rs.getString(4));	
   			    bean.setPassword(rs.getString(6));
   			    
   			}
   		 } 
   		catch (SQLException e) 
   		   {
   			
   			 e.printStackTrace();
   		   }
   		return bean;
   	}

}
	
