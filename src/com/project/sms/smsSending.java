package com.project.sms;

import java.net.HttpURLConnection;
import java.net.URL;

public class smsSending {
	

		
		public void Sms_user(String mobNo,String msg)
		{
	try {
				String userid = "ieeeglobal";
				String passwd = "123123";
				String sender="IEEEGL";
				String requestUrl = "http://www.smswave.in/panel/managetemplate.php?sender="+ sender + "&PhoneNumber=" + mobNo +"&Text="+msg+"&user=" + userid +"&password=" + passwd ; 
				//String requestUrl = "http://www.smswave.in/panel/managetemplate.php?sender="+ sender + "&PhoneNumber=" + phone +"&Text="+msg+"&user=" + userid +"&password=" + passwd ; 

			
				
				URL url = new URL(requestUrl);
				HttpURLConnection uc = (HttpURLConnection)url.openConnection();
				System.out.println("URL Connection Start....");
				
				System.out.println(uc.getResponseMessage());
				
				System.out.println("Messsage Is Sent Sucessfully...");
				uc.disconnect();
				} catch(Exception ex) {
				System.out.println(ex.getMessage());
				}

	}
		public static void main(String args[]){


			smsSending sms=new smsSending();
			
			 String userDate="11/26/2019";
				
				System.out.println("Date"+userDate);
				
			
				sms.Sms_user("7387186508","bhagyashri123");
			
			 
		
	}

}
