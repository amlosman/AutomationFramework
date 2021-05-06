package FileWrappers;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendMail {

    public static void SentTheLog()  {

        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = ReadFromPropertiesFile.getValue("Mymail");
        String pass = ReadFromPropertiesFile.getValue("pass");
        String to = ReadFromPropertiesFile.getValue("sentTo");

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, pass);

            }

        });
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f = new File("E:\\Course.txt");

                attachmentPart.attachFile(f);
                textPart.setText("This is text");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);


                message.setContent(multipart);

                System.out.println("sending...");
                // Send message
                Transport.send(message);

                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    }