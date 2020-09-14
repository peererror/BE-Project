package com.project.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.bean.UserBean;
import com.project.bean.imagebean;
import com.project.db.DBConnect;

public class ImageDB {
	
	static Connection con;
	PreparedStatement pstmt;
	
	
	
	
	
}
	public boolean InputImage(imagebean bean){
		try{
	pstmt= con.prepareStatement("INSERT INTO image (id, name, image) VALUES (?, ?, ?)");
	pstmt.setInt(1, id);
	pstmt.setString(2, name);
	pstmt.setBinaryStream(3, image);
	
	
	
	
/*	
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
					
					
					
					
					
					
					
					Part part=request.getPart("file");

					if(part!=null)
					{
					image=part.getInputStream();
					System.out.println("is size:"+image.available());
					imageName=db.extractFileName(part);
					System.out.println("name:"+imageName);
					}
					
					
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
	*/
	
	/*********************************************INPUT IMAGE TO THE DATABASE********************************************************/



	public boolean InputImage1(UserBean bean){
			try{
			 
			   Statement st1 = con.createStatement();
			   File imgfile = new File("pic.jpg");
			  
			  FileInputStream fin = new FileInputStream(imgfile);
			 
			   PreparedStatement pre =
			   con.prepareStatement("insert into Image values(?,?,?)");
			 
			   pre.setString(1,"test");
			   pre.setInt(2,3);
			   pre.setBinaryStream(3,(InputStream)fin,(int)imgfile.length());
			   pre.executeUpdate();
			   System.out.println("Successfully inserted the file into the database!");

			   pre.close();
			   con.close(); 
			}catch (Exception e1){
				System.out.println(e1.getMessage());
			}
			return resultStatus;
			}



	/*********************************************RETRIVE IMAGE FROM THE DATABASE********************************************************/

	public boolean RetriveImage(UserBean bean){
			try{
				
				Statement stmt = con.createStatement();
				ResultSet rs1 = stmt.executeQuery("select image from image");
				int i = 0;
				while (rs.next()) {
					InputStream in = rs.getBinaryStream(1);
					OutputStream f = new FileOutputStream(new File("test"+i+".jpg"));
					i++;
					int c = 0;
					while ((c = in.read()) > -1) {
						f.write(c);
					}
					f.close();
					in.close();
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			return resultStatus;
			}

	}














































	/*	while (rs.next()) {
			images = rs.getString("path");
			System.out.println(images + "\n");
			System.out.println("TESTING - READING IMAGE");
			BufferedImage img = ImageIO.read(new File(images));
			System.out.println("img = " + images);
			imagelabel = new JLabel(new ImageIcon(img));
			imagelabel.setPreferredSize(new Dimension(200, 200));
			imageselect.add(imagelabel);
		}
			
			public uploadImage
	   {
		   BufferedImage imgA = ImageIO.read(new File("/one.jpg"));
	       image1 = new JLabel(new ImageIcon(imgA));
	       image1.setPreferredSize(new Dimension(200, 200));
	       img1 = new JPanel();        
	       img1.add(image1);

	       loadcard.add(img1,"1");
	       cl2.show(loadcard,"1");

	       BufferedImage imgB = ImageIO.read(new File("/two.jpg"));
	       image2 = new JLabel(new ImageIcon(imgB));
	       image2.setPreferredSize(new Dimension(200, 200));
	       img2 = new JPanel();        
	       img2.add(image2);

	       loadcard.add(img2, "2");

	       BufferedImage imgC = ImageIO.read(new File("/three.jpg"));
	       image3 = new JLabel(new ImageIcon(imgC));
	       image3.setPreferredSize(new Dimension(200, 200));
	       img3 = new JPanel();        
	       img3.add(image3);

	       loadcard.add(img3, "3");

	       BufferedImage imgD = ImageIO.read(new File("/four.jpg"));
	       image4 = new JLabel(new ImageIcon(imgD));
	       image4.setPreferredSize(new Dimension(200, 200));
	       img4 = new JPanel();
	       img4.add(image4);

	       loadcard.add(img4, "4");

	       BufferedImage imgE = ImageIO.read(new File("/five.jpg"));

	       image5 = new JLabel(new ImageIcon(imgE));
	       image5.setPreferredSize(new Dimension(200, 200));
	       img5 = new JPanel();        
	       img5.add(image5);

	       loadcard.add(img5, "5");
	   }
	}*/


}
