package com.codeup.blogapp.services;

import com.codeup.blogapp.data.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void prepareAndSend(Post post, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("raul.c.martinez07@gmail.com"); //Insert email here in string
        msg.setTo("raul.c.martinez07@gmail.com");
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
