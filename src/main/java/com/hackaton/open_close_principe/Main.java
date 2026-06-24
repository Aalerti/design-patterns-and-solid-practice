package com.hackaton.open_close_principe;

public class Main {
    public static void main(String[] args) {
        NotificationPriceCalculator calculator = new NotificationPriceCalculator();

        Notification email = new Notification(Channel.EMAIL, "Welcome to the course!", 1);
        Notification sms = new Notification(Channel.SMS, "Your homework was checked", 2);
        Notification push = new Notification(Channel.PUSH, "New lesson is available", 3);

        printPrice(calculator, email);
        printPrice(calculator, sms);
        printPrice(calculator, push);
    }

    private static void printPrice(NotificationPriceCalculator calculator, Notification notification) {
        System.out.println(notification.channel() + " price: " + calculator.calculate(notification));
    }
}

enum Channel {
    EMAIL,
    SMS,
    PUSH
}

record Notification(Channel channel, String message, int recipientsCount) {
}

class NotificationPriceCalculator {
    double calculate(Notification notification) {
        return switch (notification.channel()) {
            case EMAIL -> 0.10 * notification.recipientsCount();
            case SMS -> 0.75 * notification.recipientsCount();
            case PUSH -> 0.25 * notification.recipientsCount();
        };
    }
}
