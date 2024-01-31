package gov.iti.jets.Controllers.services.Emails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailsService {
    final String telegraphEmail = "telegraphapp.tech.com";
    final String password = "TELEGRAPHteam2024";

    Properties prop = new Properties();

    void init() {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    }

    public void sendEmail(String receiver, String subject, String emailContent) {
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(telegraphEmail, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(telegraphEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Recipient,\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (
                MessagingException e) {
            e.printStackTrace();
        }
    }
}
