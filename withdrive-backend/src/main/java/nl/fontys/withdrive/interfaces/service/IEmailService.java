package nl.fontys.withdrive.interfaces.service;

import nl.fontys.withdrive.enumeration.ApplicationStatus;

import java.util.UUID;

public interface IEmailService {
    void sendMail(String to, String subject, String body);
    void sendApplicationNotification(String to);
    void sendApplicationResponseNotification(String to);
    void sendReviewNotification(String to);
}
