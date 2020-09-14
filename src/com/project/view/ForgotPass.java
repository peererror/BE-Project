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
import com.project.service.EmailDemo;
import com.project.validation.EmailAndMobile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
public class ForgotPass 
{
	private JFrame frame;
	private JPanel panel1, panel2;
	private JLabel userLabel,passwordLabel,loginLabel,headerLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton sendButton,clearButton, registerButton;
	Boolean resultStatus=Boolean.FALSE;
	public static String uemail;
	
	public ForgotPass() 
	 {
		frame=new JFrame("FORGOT PASS FORM");
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
		frame.setContentPane(new JLabel(new ImageIcon("images/6G9.jpg")));
		
		
		panel2.setLayout(null);
		
		headerLabel=new JLabel("Face Recognition");
		headerLabel.setBounds(150, 0, 160, 25);
		//headerLabel.setSize(headerLabel.getPreferredSize());
		headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
		headerLabel.setForeground(Color.BLACK);
		panel1.add(headerLabel);
		
		loginLabel=new JLabel("ENTER EMAIL..");
		loginLabel.setBounds(100, 10, 160, 25);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 22));
		panel2.add(loginLabel);

		userLabel = new JLabel("Email Id");
		userLabel.setBounds(10, 80, 80, 25);
		panel2.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 80, 160, 25);
		panel2.add(userText);

		
		sendButton = new JButton("Send");
		sendButton.setBounds(50, 150, 80, 25);
		panel2.add(sendButton);
		
		sendButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)	
		  {
			  String email=userText.getText();
		      boolean validEmail =EmailAndMobile.isValidEmailAddress(email);
		      
			    if(userText.getText().length()==0)
		         {
		            JOptionPane.showMessageDialog(frame,"Please Enter User Email");
		         }
			    else if(validEmail==false)
		         {       
		            JOptionPane.showMessageDialog(frame,"Please Enter Valid Email Id");  
		         }
		       
		        else
		         {   
			       UserBean bean=new UserBean();
			       bean.setEmail(email);
			       
			       UserDao ud=new UserDao();
			       
			       bean=ud.userSelect(email);
			       
			      if(bean.getUname()!=null && bean.getPassword()!="") 
			       {
			    	  //uemail=bean.getEmail();
			    	  EmailDemo ed=new EmailDemo();
					  ed.sendUserPass(email, bean.getUname(), bean.getPassword());
					  System.out.println("Email Send Successfully");
					  
					  JOptionPane.showMessageDialog(null,"Email Send Successfully");
			    	  try {
							UIManager
									.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			    	  LoginView ip=new LoginView();
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
		clearButton.setBounds(180, 150, 80, 25);
		panel2.add(clearButton);
		
		
		registerButton = new JButton("New User");
		registerButton.setBounds(100, 200, 100, 25);
		panel2.add(registerButton);
		
		
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
        
      }
	/* public static void main(String args[])
     {
 	   try 
 	    {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
 	   
	       new ForgotPass();
     }*/
	
}
