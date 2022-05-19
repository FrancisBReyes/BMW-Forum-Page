package com.example.bmwreddit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.bmwreddit.exception.SpringRedditException;
import com.example.bmwreddit.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.logging.Logger;

@Service
@AllArgsConstructor //--> Lombok
@Slf4j // --> Lombok 
class MailService {
	
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
    @Autowired
	private JavaMailSender mailSender;
    @Autowired
    private MailContentBuilder mailContentBuilder;

    //TAKES NOTIFICATIONEMAIL AS INPUT, AND INSIDE THE METHOD WE ARE CREATING
    //MIMEMESAGE BY PASSING IN THE SENDER, RECIPIENT, SUBJECT AND BODY FIELDS
    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            //MESSAGE BODY WE ARE RECEIVING FROM BUILD() METHOD
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        //CHECKING FOR EXCEPTIONS 
        try {
            mailSender.send(messagePreparator);
			LOGGER.info("Activation email sent!!");
        } catch (MailException e) {
            throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }

}
