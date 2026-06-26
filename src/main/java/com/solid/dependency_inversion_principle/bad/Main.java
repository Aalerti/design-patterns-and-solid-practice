package com.solid.dependency_inversion_principle.bad;

public class Main {
    public static void main(String[] args) {
        PasswordResetService service = new PasswordResetService();
        service.sendResetCode("Alice", "+79001234567");
        service.sendResetCode("Bob", "+79009876543");
    }
}

class PasswordResetService {
    private final SmsGateway smsGateway = new SmsGateway();

    void sendResetCode(String username, String contact) {
        String code = generateCode();
        System.out.println("Generating reset code for " + username + ": " + code);
        smsGateway.sendSms(contact, "Your password reset code: " + code);
    }

    private String generateCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
}

class SmsGateway {
    void sendSms(String phone, String message) {
        System.out.println("SMS to " + phone + ": " + message);
    }
}
