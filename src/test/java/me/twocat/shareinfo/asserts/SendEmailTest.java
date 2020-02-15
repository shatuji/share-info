package me.twocat.shareinfo.asserts;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date ;
import java.util.Properties;
import java.util.Random;

public class SendEmailTest {
  // for example, smtp.mailgun.org
  //private static final String SMTP_SERVER = "smtp.gmail.com ";

  //private static final String USERNAME = "echo.crystal711@gmail.com";
  //private static final String PASSWORD = "15385920599";
  private static final String USERNAME = "2catme@sina.com";
 private static final String PASSWORD = "jjdUjd4#j8sJgh3";

  private static final String EMAIL_TO = "pinewang@foxmail.com";


  public void sendHtmlEmail(String host, String port,
                            final String userName, final String password, String toAddress,
                            String subject, String message) throws AddressException,
    MessagingException {

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

  /**
   * Test the send html e-mail method
   *
   */
  public static void main(String[] args) {
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
    Random random = new Random();
    int randomsNumber = random.nextInt(999999);
    message += "me-" + String.format("%06d", randomsNumber);
    message += "<font color=red>Duke</font>";

    SendEmailTest mailer = new SendEmailTest();

    try {
      mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo,
        subject, message);
      System.out.println("Email sent. and send successful");
    } catch (Exception ex) {
      System.out.println("Failed to sent email.");
      ex.printStackTrace();
    }
  }
}
