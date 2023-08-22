package lk.ijse.rentabike.controller;

import javafx.scene.control.Alert;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private static final String EMAIL_HOST = "smtp.gmail.com";
    private static final String EMAIL_USERNAME = "info.rentabikesrilanka@gmail.com";
    private static final String EMAIL_PASSWORD = "kzubfbcohxeoqkdi";

    public static void sendEmail(String recipientEmail, String subject, String messageContent) {
        // Set up email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set the From address
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            // Set the To address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            // Set the Subject
            message.setSubject(subject);
            // Set the Content of the message
            message.setText(messageContent);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
            new Alert(Alert.AlertType.WARNING, "Failed to send email.").show();
        }
    }
}


