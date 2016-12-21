package com.zhu.util.email;

import com.zhu.constant.EmailConstant;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件需要使用的基本信息
 * @author  zhu
 */
public class SimpleMailSender {
	
	public MailSenderInfo getMailInfo(String toAddress,String title,String content){
		/*MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("172.16.1.100");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);    
		mailInfo.setUserName("cert-notice");
		mailInfo.setPassword("notice123!");//您的邮箱密码    
		mailInfo.setFromAddress("cert-notice@unionpay.com");    
		mailInfo.setToAddress(toAddress);    
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		return mailInfo;*/
		
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(EmailConstant.MailServerHost);
		mailInfo.setMailServerPort(EmailConstant.MailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setFromAddress(EmailConstant.FromMail);
		mailInfo.setUserName(EmailConstant.FromMailUserName);
		mailInfo.setPassword(EmailConstant.FromMailPwd);
		mailInfo.setToAddress(toAddress);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		return mailInfo;
	}
	
	/**   
	  * 以文本格式发送邮件   
	  * @param mailInfo 待发送的邮件的信息   
	  */    
	    public boolean sendTextMail(String toAddress,String title,String content) {  
	      MailSenderInfo mailInfo = getMailInfo(toAddress,title,content);
	    
	      // 判断是否需要身份认证    
	      MyAuthenticator authenticator = null;    
	      Properties pro = mailInfo.getProperties();  
	      
	      if (mailInfo.isValidate()) {    
	      // 如果需要身份认证，则创建一个密码验证器    
	    	  System.out.println("创建认证：");
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
	      }   
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress());    
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      // 创建邮件的接收者地址，并设置到邮件消息中    
	      Address to = new InternetAddress(mailInfo.getToAddress());    
	      mailMessage.setRecipient(Message.RecipientType.TO,to);    
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());    
	      // 设置邮件消息的主要内容    
	      String mailContent = mailInfo.getContent();    
	      mailMessage.setText(mailContent); 
	      //设置邮件附件
	      if(mailInfo.getAttachFileNames() != null && mailInfo.getAttachFileNames().length>0){
	    	  mailMessage.setFileName(mailInfo.getAttachFileNames()[0]);
	      }
	      // 发送邮件    
	      Transport.send(mailMessage);   
	      return true;    
	      } catch (MessagingException ex) {   
	    	  System.out.println("邮件发送失败...");
	          ex.printStackTrace();    
	      }    
	      return false;
	      //return true;
	    }    
	       
	    /** *//**   
	      * 以HTML格式发送邮件   
	      * @param mailInfo 待发送的邮件信息   
	      */    
	    public boolean sendHtmlMail(String toAddress,String title,String content){
	      /*MailSenderInfo mailInfo = getMailInfo(toAddress,title,content);
	      // 判断是否需要身份认证    
	      MyAuthenticator authenticator = null;   
	      Properties pro = mailInfo.getProperties();   
	      //如果需要身份认证，则创建一个密码验证器     
	      if (mailInfo.isValidate()) {    
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
	      }    
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress());    
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      // 创建邮件的接收者地址，并设置到邮件消息中    
	      Address to = new InternetAddress(mailInfo.getToAddress());    
	      // Message.RecipientType.TO属性表示接收者的类型为TO    
	      mailMessage.setRecipient(Message.RecipientType.TO,to);    
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());
	      //设置邮件附件
	      if(mailInfo.getAttachFileNames() != null && mailInfo.getAttachFileNames().length>0){
	    	  mailMessage.setFileName(mailInfo.getAttachFileNames()[0]);
	      }
	      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
	      Multipart mainPart = new MimeMultipart();    
	      // 创建一个包含HTML内容的MimeBodyPart    
	      BodyPart html = new MimeBodyPart();    
	      // 设置HTML内容    
	      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
	      mainPart.addBodyPart(html);    
	      // 将MiniMultipart对象设置为邮件内容    
	      mailMessage.setContent(mainPart);    
	      // 发送邮件    
	      Transport.send(mailMessage);    
	      return true;    
	      } catch (MessagingException ex) {    
	          ex.printStackTrace();    
	      }    
	      return false; */ 
	      return true;
	    }    
	
}
