package sample.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
import javax.swing.*;


import sample.CurrentUser;
import sample.DB.Configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.util.Date;
import java.util.Properties;

public class MailChecking {

    public void send() throws IOException, MessagingException {

        /*
        final String USER = "yegorf.bot@gmail.com";
        final String PASSWORD = "keklolprekol";
        final Properties properties = new Properties();
        //properties.load(new FileInputStream("C:\\Users\\yegor\\IdeaProjects\\inex\\mail.properties"));

        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", USER);
        properties.put("mail.smtps.auth", "true");
        properties.put("mail.smtps.sendpartial", "true");

        /*
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress("yegorf.bot"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("yegorkhohlov@gmail.com"));
        message.setSubject("hello");
        message.setText("Hi this is my text message");

        Transport tr = mailSession.getTransport();
        tr.connect(null, "keklolprekol");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();


        Session session = Session.getDefaultInstance(properties);
        Transport transport = session.getTransport();
        transport.connect("smtp.gmail.com", 465, USER, PASSWORD);

        MimeMessage message = new MimeMessage(session);
        message.setSubject("hello");
        message.setText("Hi this is my text message");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("yegorkhohlov@gmail.com"));
        message.setSentDate(new Date());

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        */

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", 465);

        Session s = Session.getDefaultInstance(properties,

                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yegorf.bot@gmail.com", "keklolprekol");
            }
                }
        );


        try {
            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress("yegorf.bot@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yegorkhohlov@gmail.com"));
            message.setSubject("Test");
            message.setText("hello");
            Transport.send(message);

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }


    }
}
