package util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {

    public static void sendEmail(
            String to,
            String subject,
            String message) {

        final String fromEmail =
                "yourgmail@gmail.com";

        final String password =
                "your-app-password";

        Properties props =
                new Properties();

        props.put(
                "mail.smtp.host",
                "smtp.gmail.com");

        props.put(
                "mail.smtp.port",
                "587");

        props.put(
                "mail.smtp.auth",
                "true");

        props.put(
                "mail.smtp.starttls.enable",
                "true");

        Session session =
                Session.getInstance(
                        props,
                        new Authenticator() {

                    @Override
                    protected PasswordAuthentication
                            getPasswordAuthentication() {

                        return new PasswordAuthentication(
                                fromEmail,
                                password);
                    }
                });

        try {

            Message msg =
                    new MimeMessage(session);

            msg.setFrom(
                    new InternetAddress(fromEmail));

            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to));

            msg.setSubject(subject);

            msg.setText(message);

            Transport.send(msg);

            System.out.println(
                    "Email Sent Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}