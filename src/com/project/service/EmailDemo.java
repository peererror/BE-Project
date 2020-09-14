package com.project.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailDemo {

   Boolean flag=Boolean.FALSE;
   public boolean sendEmail(String to)
   {
	    String subject="Welcome";
		String addtext="Registration Success";


		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("projectjava8@gmail.com","project888");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress());//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject(subject);  
		   message.setText(addtext);  
		     
		   
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully");  
		   flag=true;
		   //response.sendRedirect("emailsuccess.jsp");
		  }
		  catch (MessagingException e) 
		  {
			  throw new RuntimeException(e);
		}
		return flag;  
   }
   
   public void sendImageAndKey(String to, String key, String path)
   {
	    System.out.println("Key in email class= "+key);
		  //Get the session object  
	      String subject="Secret Key";
		  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("projectjava8@gmail.com","project888");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress());//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject(subject);
		   //message.setText("Hello!!! "+"Secret Key="+key);
		     
		    MimeMultipart multipart = new MimeMultipart("related");

	        // first part  (the html)
	        BodyPart messageBodyPart = new MimeBodyPart();
	        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
	        messageBodyPart.setContent(htmlText, "text/html");
	        messageBodyPart.setText("Secret Key="+key);
	        // add it
	        multipart.addBodyPart(messageBodyPart);
	        
	        // second part (the image)
	        messageBodyPart = new MimeBodyPart();
	        DataSource fds = new FileDataSource
	          (path);
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID","<image>");

	        // add it
	        multipart.addBodyPart(messageBodyPart);

	        // put everything together
	        message.setContent(multipart);
		   
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully");  
		   //response.sendRedirect("emailsuccess.jsp");
		  }
		  catch (MessagingException e) 
		  {
			  throw new RuntimeException(e);
		}  
   }
   
   public void sendKey(String to,String key)
   {
	      String subject="Secret Key";
	      
	      Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("projectjava8@gmail.com","project888");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress());//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject(subject);
		   message.setText("Hello  "+to+"\n\nSecret Key="+key);
		   
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("Key sent successfully");  
		   //response.sendRedirect("emailsuccess.jsp");
		  }
		  catch (MessagingException e) 
		  {
			  e.printStackTrace();  
	   
   }
}
	   
   public void sendUserPass(String to, String uname, String upass)
	   {
		   Properties props = new Properties();  
			  props.put("mail.smtp.host", "smtp.gmail.com");  
			  props.put("mail.smtp.socketFactory.port", "465");  
			  props.put("mail.smtp.socketFactory.class",  
			            "javax.net.ssl.SSLSocketFactory");  
			  props.put("mail.smtp.auth", "true");  
			  props.put("mail.smtp.port", "465");  
			   
			  Session session = Session.getDefaultInstance(props,  
			   new javax.mail.Authenticator() {  
			   protected PasswordAuthentication getPasswordAuthentication() {  
			   return new PasswordAuthentication("projectjava8@gmail.com","project888");//change accordingly  
			   }  
			  });  
			   
			  //compose message  
			  try {  
			   MimeMessage message = new MimeMessage(session);  
			   message.setFrom(new InternetAddress());//change accordingly  
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			   message.setSubject("Password");
			   message.setText("Hello  "+to+"\nYour Username is:="+uname+"\nYour Password is:= "+upass);
			     
			   
			   //send message  
			   Transport.send(message);  
			  
			   System.out.println("message sent successfully");  
			   //response.sendRedirect("emailsuccess.jsp");
			  }
			  catch (MessagingException e) 
			  {
				  e.printStackTrace();  
		   
	   }
   }
}
