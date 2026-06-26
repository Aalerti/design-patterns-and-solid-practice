package com.solid.dependency_inversion_principle.good;

public class Main {
    public static void main(String[] args) {
        PasswordResetService service = new PasswordResetService(new SmsGateway());
        service.sendResetCode("Alice", "+79001234567");
        service.sendResetCode("Bob", "+79009876543");
    }
}

class PasswordResetService {
    private final NotificationSender notificationSender;

    public PasswordResetService(NotificationSender smsGateway) {
        this.notificationSender = smsGateway;
    }

    void sendResetCode(String username, String contact) {
        String code = generateCode();
        System.out.println("Generating reset code for " + username + ": " + code);
        notificationSender.send(contact, "Your password reset code: " + code);
    }

    private String generateCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
}

interface NotificationSender {
    void send(String contact, String message);
}

class SmsGateway implements NotificationSender {

    @Override
    public void send(String phone, String message) {
        System.out.println("SMS to " + phone + ": " + message);
    }
}

class EmailGateway implements NotificationSender {
    @Override
    public void send(String email, String message) {
        System.out.println("Email to " + email + ": " + message);
    }
}
