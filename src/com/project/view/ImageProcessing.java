package com.project.view;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;






import com.project.algo.FeatureExtraction;
import com.project.algo.PCA;
import com.project.bean.UserBean;
import com.project.dao.UserDao;

public class ImageProcessing extends javax.swing.JFrame 
{
  Connection connection=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  String filePath=null;
  String filePath1=null;
  public static String FileName=null;
  String tmp="";
  String appPath1=null;
  String outImg1=null;
  String outImg2=null;
  String fileName=null;
  String fileName1=null;

  File source, dest;
 
  
//E:\Workspace\BE 70%\DynamicFaceRecognition\src\com\project\images
  String Path="E:/Workspace/BE 70%/DynamicFaceRecognition/";
  
	
  String appPath=Path+"src/com/project/images";
  
  public ImageProcessing() 
  {

   initComponents();
   setSize(1100,770);
   //setLocation(80,100);
   setLocationRelativeTo(null);
   setVisible(true);
   
  }

//1048576 Size limit allowed for Image storage in MySQL.
  public void showImage()
  {
	  jScrollPane2.setText("");
	  
	  try
	  {
		//display image .... 
		
		  	JFileChooser chooser=new JFileChooser(new File("C:\\Users\\admin\\Pictures\\Face Images"));// Choose the file from the directory

		  	chooser.setMultiSelectionEnabled(false);
		  	chooser.setVisible(true);

		  	chooser.showOpenDialog(this);

		  	File file=chooser.getSelectedFile();
		  	FileName=file.getName();
		  	if(file!=null)
		  	{
		  		filePath=file.getPath();
			  	
		  		JOptionPane.showMessageDialog(this, "Image Uploaded Successfully!!!");
			  	
			  	path.setText("File path:-"+" "+filePath);
			  	showimage.setIcon(new ImageIcon(filePath));
			  	filename.setText(file.getName());
			  	
			  	file=chooser.getSelectedFile();
			  	BufferedImage probeImage = ImageIO.read(file);
			  	source=new File(appPath+"/probeimage.jpg");
		        ImageIO.write(probeImage, "jpg", source);
			  	
			  	dest = new File(appPath);
			  	
		        if (!dest.exists()) {
		            if (dest.mkdir()) {
		                System.out.println("Directory is created!");
		            } else {
		                System.out.println("Failed to create directory!");
		            }
		        }
	          

				fileName=filename.getText();
			   
			 	fileName=fileName.substring(0,fileName.indexOf("."));
			 }
		  	else
		  	{
		  		JOptionPane.showMessageDialog(this,"Please select image");
		  	}
	  }
   catch(Exception e)
   {
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
   }
}
  
public void showOriginalImage()
{
	try
	  {
		 
		  	//File file=new File(appPath+"/"+filename.getText());
		  	if(source!=null)
		  	{
		  		filePath=source.getPath();
		  	}
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		showimage.setIcon(new ImageIcon(filePath));
		  	} 
	  }
	catch(Exception e)
	{
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
	}
}

public void cropImage()
{
	try
	  {    
		   File file=new File(appPath+"/"+filename.getText());
		BufferedImage cropImg=FeatureExtraction.toCrop(source);
		  
		   File file1=new File(appPath+"/Img.jpg");
		   
		   ImageIO.write(cropImg, "jpg", file1);
		   
		   JOptionPane.showMessageDialog(this, "Extract Successfully!!!");
		  	if(file1!=null)
		  	{
		  		filePath=file1.getPath();
		  	}
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		showimage.setIcon(new ImageIcon(filePath));
		  	} 
	  }
	catch(Exception e)
	{
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
	}
}




public void showFeatureExtractionImage()
{
	try
	  {     File file=new File(appPath+"/crop.jpg");
		   //File file=new File(appPath+"/Img.jpg");
		   BufferedImage extractImg=FeatureExtraction.featureExtraction(file);
		   //BufferedImage filterImg=MedianFilter.medianFilter(file);
		   file=new File(appPath+"/extractImg.jpg");
		 //  file=new File(appPath+"/FilteredImg.jpg");
		   
		   ImageIO.write(extractImg, "jpg", file);
		   
		   JOptionPane.showMessageDialog(this, "Extract Successfully!!!");
		  	if(file!=null)
		  	{
		  		filePath=file.getPath();
		  	}
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		showimage.setIcon(new ImageIcon(filePath));
		  		System.out.println("filePath1==="+filePath);
		  	} 
	  }
	catch(Exception e)
	{
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
	}
}

public void showCluster()
{
	try
	  {   File file=new File(appPath+"/extractImg.jpg");
	   
		  // File file=new File(appPath+"/FilteredImg.jpg");
		   
		   BufferedImage extractImg=ImageIO.read(file);
		    
		   String[] dstpath={appPath+"/cluster1.jpg",appPath+"/cluster2.jpg",appPath+"/cluster3.jpg",appPath+"/cluster4.jpg"};
		   for(int i=0; i<dstpath.length;i++)
		   {
		    System.out.println("path= "+dstpath[i]);
		   }
		    
		   PCA pca=new PCA();
		   int k=4;
		   pca.imagecluster(k,extractImg,dstpath);
		    
		   File file1=new File(appPath+"/cluster1.jpg");
		   File file2=new File(appPath+"/cluster2.jpg");
		   File file3=new File(appPath+"/cluster3.jpg");
		   File file4=new File(appPath+"/cluster4.jpg");
		   
		   String filepath1=null;
		   String filepath2=null;
		   String filepath3=null;
		   String filepath4=null;
		   
		   JOptionPane.showMessageDialog(this, "Collected  Successfully!!!");
		   
		  	if(file1!=null && file2!=null && file3!=null && file4!=null)
		  	{
		  		filepath1=file1.getPath();
		  		filepath2=file2.getPath();
		  		filepath3=file3.getPath();
		  		filepath4=file4.getPath();
		  	}
		  	if(filepath1!=null && filepath2!=null && filepath3!=null && filepath4!=null)
		  	{
		  		//path.setText("File path:-"+" "+filePath);
		  		clustlbl1.setIcon(new ImageIcon(filepath1));
		  		clustlbl2.setIcon(new ImageIcon(filepath2));
		  		clustlbl3.setIcon(new ImageIcon(filepath3));
		  		clustlbl4.setIcon(new ImageIcon(filepath4));
		  	} 
	  }
	catch(Exception e)
	{
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
	}
}

private void initComponents() 
   {

     jLabel1 = new javax.swing.JLabel();
     path = new javax.swing.JLabel();
     filename = new javax.swing.JLabel();
     showimage = new javax.swing.JLabel();
     
     clustlbl1= new javax.swing.JLabel();
     clustlbl2= new javax.swing.JLabel();
     clustlbl3= new javax.swing.JLabel();
     clustlbl4= new javax.swing.JLabel();
     
     browse_btn = new javax.swing.JButton();
     original_btn = new javax.swing.JButton();
     crop_btn = new javax.swing.JButton(); 
   extract_btn = new javax.swing.JButton();
     pca_btn = new javax.swing.JButton();
     
     back_btn= new javax.swing.JButton();
     exit_btn= new javax.swing.JButton();
     
     mainPanel= new javax.swing.JPanel();
     TextArea = new javax.swing.JTextArea();
     
     jScrollPane1 = new javax.swing.JScrollPane();
     jScrollPane2 = new javax.swing.JTextArea();	

     setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
     getContentPane().setLayout(null);
     
     setContentPane(new JLabel(new ImageIcon("images\\3D2.jpg")));

     
     jLabel1.setText("FACE RECOGNITION SYSTEM");
     jLabel1.setFont(new Font("Arial", Font.BOLD, 20));
     jLabel1.setForeground(Color.BLACK);
     getContentPane().add(jLabel1);
     jLabel1.setBounds(330, 30, 600, 16);

     /*jScrollPane1.setViewportView(showimage);
     getContentPane().add(jScrollPane1);
     jScrollPane1.setBounds(330, 70, 400, 375);*/
     
     mainPanel.revalidate();
     mainPanel.add(showimage);
     getContentPane().add(mainPanel);
     mainPanel.setBounds(330, 70, 400, 450);
     
     browse_btn.setText("Select Image");
     //browse_btn.setForeground(new java.awt.Color(51, 51, 255));
     browse_btn.setForeground(Color.BLACK);
     getContentPane().add(browse_btn);
     browse_btn.setBounds(150, 70, 150, 30);
     browse_btn.addActionListener(new ActionListener() 
     {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			browse_btnActionPerformed(e);	
			
		}
	});
    
     original_btn.setText("Original Image");
     original_btn.setForeground(Color.BLACK);
     getContentPane().add(original_btn);
     original_btn.setBounds(150, 110, 150, 30);
     original_btn.addActionListener(new ActionListener() 
     {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			original_btnActionPerformed(e);	
			
		}
	});
     
    crop_btn.setText("face Image");
    crop_btn.setForeground(Color.BLACK);
    getContentPane().add(crop_btn);
    crop_btn.setBounds(150, 150, 150, 30);
    crop_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			crop_btnActionPerformed(e);	
			
		}
	});
     
    extract_btn.setText("FeatureExtract Image");
    extract_btn.setForeground(Color.BLACK);
    getContentPane().add(extract_btn);
    extract_btn.setBounds(150, 190, 150, 30);
    extract_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			extract_btnActionPerformed(e);	
			
		}
	});
    
    pca_btn.setText("PCA Result");
    pca_btn.setForeground(Color.BLACK);
    getContentPane().add(pca_btn);
    pca_btn.setBounds(150, 230, 150, 30);
    pca_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			pca_btnActionPerformed(e);	
			mainPanel.removeAll();
			mainPanel.add(clustlbl1);
			mainPanel.revalidate();
			mainPanel.add(clustlbl2);
			mainPanel.revalidate();
			mainPanel.add(clustlbl3);
			mainPanel.revalidate();
			mainPanel.add(clustlbl4);
			mainPanel.revalidate();
			
			/*jScrollPane1.repaint();
			jScrollPane1.setViewportView(clustlbl1);
			jScrollPane1.setViewportView(clustlbl2);
			jScrollPane1.setViewportView(clustlbl3);
			jScrollPane1.setViewportView(clustlbl4);*/
		}
	});
    
 
   back_btn.setText("Back");
   back_btn.setForeground(Color.BLACK);
   getContentPane().add(back_btn);
   back_btn.setBounds(150, 590, 150, 30);
   back_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			UserHome ip=new UserHome();
			dispose();
		}
	});
    
   exit_btn.setText("Exit");
   exit_btn.setForeground(Color.BLACK);
   getContentPane().add(exit_btn);
   exit_btn.setBounds(150, 630, 150, 30);
   exit_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			
			File[] flist=dest.listFiles();
	     	   if(flist.length>0)
	     	   {
	     		   for(File f:flist)
	     		   {
	     			  f.delete(); 
	     		   }
	     	   }
			dispose();
		}
	});
   
   path.setFont(new Font("Arial", Font.BOLD, 16));
   path.setForeground(Color.BLACK);
   getContentPane().add(path);
   path.setBounds(20, 680, 1000, 30);
            
     pack();
     
  }  

  private void browse_btnActionPerformed(java.awt.event.ActionEvent evt) 
   { 
	   showImage();  
   } 
  private void original_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showOriginalImage();  
  } 
  private void crop_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  cropImage();
  } 
  private void extract_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	 
  }
  
  private void pca_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showCluster();
  }


  /* public static void main(String args[])
   {
	   try 
	    {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
       new ImageProcessing().setVisible(true);
   }*/


   // Variables declaration - do not modify 
   private javax.swing.JButton browse_btn,original_btn,crop_btn,extract_btn, pca_btn,back_btn, exit_btn;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel showimage,clustlbl1,clustlbl2,clustlbl3,clustlbl4;
   private javax.swing.JLabel path,filename;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JPanel mainPanel;
   private javax.swing.JTextArea jScrollPane2,TextArea;

   // End of variables declaration 

    private boolean check() 
    {
      if(filePath!=null) 
      {
       if(filePath.endsWith(".jpeg")||filePath.endsWith(".gif")||filePath.endsWith(".jpg")||filePath.endsWith(".JPEG")||filePath.endsWith(".GIF")||filePath.endsWith(".JPG")||filePath.endsWith(".png"))
        {
         return true;
        }
        return false;
       }
       return false;
    }
}