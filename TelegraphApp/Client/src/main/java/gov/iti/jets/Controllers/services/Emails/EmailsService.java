package gov.iti.jets.Controllers.services.Emails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailsService {
    final String telegraphEmail = "telegraphapp.tech@gmail.com";
    final String password = "rcvubcggikynyudf";

    Properties prop = new Properties();

    void init() {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    public void sendEmail(String receiver, String subject, String emailContent) {
        init();
        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(telegraphEmail, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(receiver, true)
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Recipient,\n\n Please do not spam my email!");
            System.out.println("Sending message...");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println("Error while sending message : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
