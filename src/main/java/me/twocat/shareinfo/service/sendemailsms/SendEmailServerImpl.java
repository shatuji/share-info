package me.twocat.shareinfo.service.sendemailsms;

import me.twocat.shareinfo.service.redis.RedisServiceApiImpl;
import me.twocat.shareinfo.util.pojotuils.GeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Service
public class SendEmailServerImpl {

  @Autowired
  RedisServiceApiImpl redisServiceApi;
  // for example, smtp.mailgun.org
  //private static final String SMTP_SERVER = "smtp.gmail.com ";
  private static final String USERNAME = "wang1530185777@sina.com";
  private static final String PASSWORD = "15156763209";//jjdUjd4#j8sJgh3

  private static final String EMAIL_FROM = "2catme@sina.com";
  private static final String EMAIL_TO = "pinewang@foxmail.com";

  private void sendHtmlEmail(String host, String port,
                            final String userName, final String password, String toAddress,
                            String subject, String message)
    throws AddressException,MessagingException {

    // sets SMTP server properties
    Properties properties = new Properties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");

    // creates a new session with an authenticator
    Authenticator auth = new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
      }
    };

    Session session = Session.getInstance(properties, auth);

    // creates a new e-mail message
    Message msg = new MimeMessage(session);

    msg.setFrom(new InternetAddress(userName));
    InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
    msg.setRecipients(Message.RecipientType.TO, toAddresses);
    msg.setSubject(subject);
    msg.setSentDate(new Date());
    // set plain text message
    msg.setContent(message, "text/html");

    // sends the e-mail
    Transport.send(msg);

  }

  public void sendEmail(){
    // SMTP server information
    String host = "smtp.sina.com";
    String port = "587";
    String mailFrom = USERNAME;
    String password = PASSWORD;

    // outgoing message information
    String mailTo = EMAIL_TO;
    String subject = "Hello my friend";

    // message contains HTML markups
    String message = "<i>Greetings!</i><br>";
    message += "<b>Wish you a nice day!</b><br>";
    //it will generator six digit random number
    //from 0 to 9999
    message += "me-" + GeneratorUtils.generatorRandom6Digit(6);

    message += "<font color=red></font>";

    try {
        sendHtmlEmail(host, port, mailFrom, password, mailTo,
        subject, message);
      System.out.println("Email sent. and send successful");
    } catch (Exception ex) {
      System.out.println("Failed to sent email.");
      ex.printStackTrace();
    }
  }


  /**
   * Test the send html e-mail method
   *
   */
  public static void main(String[] args) {
    SendEmailServerImpl sendEmailServer = new SendEmailServerImpl();
    sendEmailServer.sendEmail();
  }
}
