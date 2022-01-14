package nl.fontys.withdrive.service;

import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.interfaces.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender mailSender;
    private final SimpleMailMessage preConfiguredMessage;

    @Autowired
    public EmailService(JavaMailSender mailSender, SimpleMailMessage preConfiguredMessage) {
        this.mailSender = mailSender;
        this.preConfiguredMessage = preConfiguredMessage;
    }

    /**
     * This method will send compose and send the message
     * */
    public void sendMail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    /**
     * This method will send a pre-configured message
     * */
    public void sendApplicationNotification(String to)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setTo(to);
        mailMessage.setSubject("A user has applied for your trip!");
        mailMessage.setText("Dear user,\n" +
                "You have received a new application for one of your upcoming trips.\n" +
                "Please check your driver profile and react!\n\n"+
                "Kind regards,\n" +
                "withdrive Team");
        mailSender.send(mailMessage);
    }

    @Override
    public void sendApplicationResponseNotification(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setTo(to);
        mailMessage.setSubject("A driver has replied to your application!");
        mailMessage.setText("Dear user,\n" +
                "You have received a response to your application.\n" +
                "Please check your profile!\n"+
                "Kind regards,\n" +
                "withdrive Team");
        mailSender.send(mailMessage);
    }

    @Override
    public void sendReviewNotification(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setTo(to);
        mailMessage.setText("Dear user," +
                "A trip you are part of has begun" +
                "Please leave a review on your driver!");
        mailSender.send(mailMessage);
    }

}