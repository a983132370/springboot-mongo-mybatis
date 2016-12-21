package email;

public class MailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	      SimpleMailSender sms = new SimpleMailSender();  
          sms.sendTextMail("939855105@qq.com","邮件title","邮件content");//发送文体格式
	}

}
