package nl.fontys.withdrive.interfaces.service;

public interface IEmailService {
    void sendMail(String to, String subject, String body);
    void sendApplicationNotification(String to);
    void sendApplicationResponseNotification(String to);
}
