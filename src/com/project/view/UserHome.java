
package com.project.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UserHome 
{
	JFrame frame;
	
	private JPanel panel1,panel2,panel3;
	private JLabel label1;
	private JButton homeButton,uploadButton, back_btn;
	
	
   public UserHome()
   {
 	    frame = new JFrame("UserHome");
	    frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		panel1=new JPanel();
		panel1.setBounds(20,50,750,80);
		
		panel2=new JPanel();
		panel2.setBounds(20,130,500,500);
		panel2.setLayout(null);
		
		label1=new JLabel("Face Recognition");
		label1.setBounds(10,10,500,40);
		label1.setFont(new Font("Arial", Font.BOLD, 22));
		label1.setForeground(Color.BLACK);
		panel1.add(label1);
		panel1.setOpaque(false);
		
		homeButton = new JButton("Home");
		homeButton.setBounds(300,40,200,40);
		homeButton.setForeground(Color.BLACK);
		//homeButton.setForeground(new java.awt.Color(51, 102, 255));
		panel2.add(homeButton);
		panel2.setOpaque(false);
		
		homeButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)	
		  {
			
			 Home home=new Home();
			 //login.setVisible(true);
			 frame.dispose();
		  }
		  
		}
		);
		
		uploadButton = new JButton("upload probeImage");
		uploadButton.setBounds(300,130,200,40);
		uploadButton.setForeground(Color.BLACK);
		panel2.add(uploadButton);
		panel2.setOpaque(false);
			
		uploadButton.addActionListener(new ActionListener()
	       {
	         public void actionPerformed(ActionEvent e)	
	          {
		
		        ImageProcessing ip=new ImageProcessing();
		        //USI.setVisible(true);
		        frame.dispose();
	          }
	  
	       }
		);
		
		
		back_btn= new JButton("Back");
		back_btn.setBounds(300,220,200,40);
		back_btn.setForeground(Color.BLACK);
		panel2.add(back_btn);
		panel2.setOpaque(false);
		
		back_btn.addActionListener(new ActionListener()
	       {
	         public void actionPerformed(ActionEvent e)	
	          {
		
		       LoginView USI=new LoginView();
		       frame.dispose();
	          }
	  
	       }
		);
		
	    frame.setContentPane(new JLabel(new ImageIcon("images\\BG7.jpg")));
	 	frame.add(panel1);
	 	frame.add(panel2);
	 	    
	 	frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
     }
  
       /*public static void main(String args[])
        {
	       new UserHome();
        }*/
}
