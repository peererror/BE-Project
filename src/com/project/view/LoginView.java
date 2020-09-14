package com.project.view;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.project.bean.UserBean;
import com.project.dao.UserDao;
import com.project.validation.EmailAndMobile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
public class LoginView 
{
	private JFrame frame;
	private JPanel panel1, panel2;
	private JLabel userLabel,passwordLabel,loginLabel,headerLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton loginButton,clearButton, registerButton, forgot_btn, back_btn;
	Boolean resultStatus=Boolean.FALSE;
	public static String uemail;
	
	public LoginView() 
	 {
		frame=new JFrame("LOGIN FORM");
	    frame.setSize(800,600);
	    //frame.setLocation(250,200);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setBounds(200,160,600,440);
		
		panel1=new JPanel();
		panel1.setBounds(20,50,750,120);
		
		panel2 = new JPanel();
		panel2.setBounds(250,150,300,400);
		
		
		panel2.setOpaque(false);
		frame.setLayout(null);
		frame.setContentPane(new JLabel(new ImageIcon("images/BG7.jpg")));
		
		
		panel2.setLayout(null);
		
		headerLabel=new JLabel("Face Recognition");
		headerLabel.setBounds(150, 0, 160, 25);
		//headerLabel.setSize(headerLabel.getPreferredSize());
		headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
		headerLabel.setForeground(Color.BLACK);
		panel1.add(headerLabel);
		
		loginLabel=new JLabel("LOGIN HERE...");
		loginLabel.setBounds(100, 10, 160, 25);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 22));
		panel2.add(loginLabel);

		userLabel = new JLabel("UserEmail");
		userLabel.setBounds(10, 60, 80, 25);
		panel2.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 60, 160, 25);
		panel2.add(userText);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 100, 80, 25);
		panel2.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 100, 160, 25);
		panel2.add(passwordText);

		loginButton = new JButton("Login");
		loginButton.setBounds(50, 150, 100, 25);
		panel2.add(loginButton);
		
		loginButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)	
		  {
			  String email=userText.getText();
		      boolean validEmail =EmailAndMobile.isValidEmailAddress(email);
		      
		      String pass= passwordText.getText();
			   
			    if(userText.getText().length()==0)
		         {
		            JOptionPane.showMessageDialog(frame,"Please Enter User Email");
		         }
			    else if(validEmail==false)
		         {       
		            JOptionPane.showMessageDialog(frame,"Please Enter Valid Email Id");  
		         }
		        else if(passwordText.getText().length()==0)
		         {
		            
		            JOptionPane.showMessageDialog(frame,"Please Enter Password");
		             
		         }
		        else
		         {   
			       UserBean bean=new UserBean();
			       bean.setEmail(email);;
			       bean.setPassword(pass);
			       
			       UserDao ud=new UserDao();
			   
			   
			      if(resultStatus=ud.loginCheck(bean)) 
			       {
			    	  uemail=bean.getEmail();
			    	  
			    	  try {
							UIManager
									.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			    	  JOptionPane.showMessageDialog(null,"Login Successfully!!!");
			    	  UserHome ip=new UserHome();
			    	  //ip.setVisible(true);
				      frame.dispose();
			       } 
			     else
			       {

				     JOptionPane.showMessageDialog(null,"Wrong Password / Username");
				     userText.setText("");
				     passwordText.setText("");
				     userText.requestFocus();
			       }
		        }
		    }
		  
		   }
		   );
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(180, 150, 100, 25);
		panel2.add(clearButton);
		
		
		registerButton = new JButton("New User");
		registerButton.setBounds(50, 200, 100, 25);
		panel2.add(registerButton);
		
		forgot_btn= new JButton("Forgot Pass");
		forgot_btn.setBounds(180, 200, 100, 25);
		panel2.add(forgot_btn);
		
		back_btn= new JButton("Back");
		back_btn.setBounds(120, 250, 100, 25);
		panel2.add(back_btn);
		
		registerButton.addActionListener(new java.awt.event.ActionListener()
		   {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	registerButtonActionPerformed(evt);
            }
        });
		
		clearButton.addActionListener(new java.awt.event.ActionListener()
		   {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	clearButtonActionPerformed(evt);
            }
        });
		
		forgot_btn.addActionListener(new java.awt.event.ActionListener()
		   {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	  
        	 ForgotPass f=new ForgotPass();
        	 frame.dispose();
         }
     });
		
		back_btn.addActionListener(new java.awt.event.ActionListener()
		   {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
     	  
     	 Home f=new Home();
     	 frame.dispose();
      }
    });
		
		
	    panel1.setOpaque(false);
		panel2.setOpaque(false);
		
		frame.add(panel1);
		frame.add(panel2);
		frame.setVisible(true);
	 }
	
	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) 
	   {
		    try {
			    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		        } catch (Exception e1)
		         {
			     e1.printStackTrace();
	           	}
		
	        Register register=new Register();
	        //register.setVisible(true);
	        frame.dispose();
	        
	   }
	
	private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) 
	  {
        
		userText.setText("");
        passwordText.setText("");
      
      }
	
	
	
}
