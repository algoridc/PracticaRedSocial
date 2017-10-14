package auxiliares;


/*
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailSender {
	
	private String mailSMTPServer;
	private String mailSMTPServerPort;
	private String mailPwdCuentaCorreo;
	private String miCuentaCorreo;
	
	public void sendMail(String destinatario, String pwd){
		
		Properties props = new Properties();
		
		mailSMTPServer="smtp.googleemail.com";
		mailSMTPServerPort="465";
		mailPwdCuentaCorreo="atencioncliente1234";
		miCuentaCorreo="atencion.cliente.dissw@gmail.com";
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host", mailSMTPServer);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.user", miCuentaCorreo);
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", "mailSMTPServerPort");
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		SimpleAuth auth = null;
		auth = new SimpleAuth(miCuentaCorreo, mailPwdCuentaCorreo);
		
		Session session = Session.getDefaultInstance (props, auth);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		
		try{
			
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			msg.setFrom(new InternetAddress(miCuentaCorreo));
			msg.setSubject("subject");
			msg.setContent(pwd, "Su contraseña es: "+pwd);
		}
		
		catch (Exception e){ }
		
		Transport tr;
		try{
			tr = session.getTransport("stmp");
			tr.connect(mailSMTPServer, miCuentaCorreo, mailPwdCuentaCorreo);
			msg.saveChanges();
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		}
		
		catch (Exception e){}
		
		
	}
	
}

class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;
	
	public SimpleAuth(String user, String pwd){
		username = user;
		password = pwd;
		
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication(){	
		return new PasswordAuthentication (username, password);
	}

	
	
}

*/

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private static String userName = "atencion.cliente.dissw@gmail.com";
	private static String password = "atencioncliente1234";

	public SendMail() {
		
	}
	
	private static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		return session;
	}
	public void sendMail(String email, String pwd) throws Exception{
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject("Recupera tu contraseña");
			message.setText("La contraseña para acceder a tu cuenta es:"+pwd);
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}






