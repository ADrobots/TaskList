package com.tasklist;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {
	private String username;
    private String password;
    private Properties properties;

    public Sender(String username, String password) {
        this.username = username;
        this.password = password;

        properties= new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.timeweb.ru");
        properties.put("mail.smtp.port", "25");
    }

    public void send(String subjectm, String text, String from, String to) throws MessagingException {
        Session session=Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);//�� ����
            message.setFrom(new InternetAddress(username));//����
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subjectm);//��������� ������
            message.setText(text);//����������

            Transport.send(message);
        }catch (MessagingException e){
            throw  new RuntimeException(e);
        }
    }

}
