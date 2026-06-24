package com.hackaton.open_close_principe.good;

public class Main {
    public static void main(String[] args) {


        Notification email = new EmailNotification("Welcome to the course!", 1);
        Notification sms = new SMSNotification( "Your homework was checked", 2);
        Notification push = new PushNotification( "New lesson is available", 3);
        Notification telegram = new TelegramNotification("New pattern", 1);

        printPrice(email);
        printPrice(sms);
        printPrice(push);
        printPrice(telegram);
    }

    private static void printPrice(Notification notification) {
        System.out.println(notification.getType() + " price: " + notification.calculatePrice());
    }
}


interface Notification {
    double calculatePrice();
    Channel getType();
}

enum Channel {
    SMS,
    EMAIL,
    Push,
    Telegram
}


class EmailNotification implements Notification {
    private static final Channel type = Channel.EMAIL;
    public static final double tax = 0.1;
    private int recipientsCount;
    private String message;

    public EmailNotification(String message, int recipientsCount ) {
        this.recipientsCount = recipientsCount;
        this.message = message;
    }

    @Override
    public double calculatePrice() {
        return tax * recipientsCount;
    }

    @Override
    public Channel getType() {
        return type;
    }

}

class SMSNotification implements Notification {
    private static final Channel type = Channel.SMS;
    public static final double tax = 0.75;
    private int recipientsCount;
    private String message;

    public SMSNotification(String message, int recipientsCount) {
        this.recipientsCount = recipientsCount;
        this.message = message;
    }

    @Override
    public double calculatePrice() {
        return tax * recipientsCount;
    }

    @Override
    public Channel getType() {
        return type;
    }
}

class PushNotification implements Notification {
    private static final Channel type = Channel.Push;
    public static final double tax = 0.25;
    private int recipientsCount;
    private String message;

    public PushNotification(String message, int recipientsCount) {
        this.recipientsCount = recipientsCount;
        this.message = message;
    }

    @Override
    public double calculatePrice() {
        return tax * recipientsCount;
    }

    @Override
    public Channel getType() {
        return type;
    }
}

class TelegramNotification implements Notification {
    private static final Channel type = Channel.Telegram;
    public static final double tax = 0.4;
    private int recipientsCount;
    private String message;

    public TelegramNotification(String message, int recipientsCount) {
        this.recipientsCount = recipientsCount;
        this.message = message;
    }

    @Override
    public double calculatePrice() {
        return tax * recipientsCount;
    }

    @Override
    public Channel getType() {
        return type;
    }
}

