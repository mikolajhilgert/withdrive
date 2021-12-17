package nl.fontys.withdrive.interfaces.service;

import nl.fontys.withdrive.enumeration.ApplicationStatus;

import java.util.UUID;

public interface IEmailService {
    void sendMail(String to, String subject, String body);
    void sendApplicationNotification(UUID to);
    void sendApplicationResponseNotification(UUID to);
    void sendReviewNotification(UUID to);
}
