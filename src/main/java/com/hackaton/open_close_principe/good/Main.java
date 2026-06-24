package com.hackaton.open_close_principe.good;

public class Main {
    public static void main(String[] args) {
        NotificationCalculator notificationCalculator = new NotificationCalculator();

        Notification email = new EmailNotification("Welcome to the course!", 1);
        Notification sms = new SMSNotification("Your homework was checked", 2);
        Notification push = new PushNotification("New lesson is available", 3);
        Notification telegram = new TelegramNotification("New pattern", 1);

        printPrice(email, notificationCalculator);
        printPrice(sms, notificationCalculator);
        printPrice(push, notificationCalculator);
        printPrice(telegram, notificationCalculator);
    }

    private static void printPrice(Notification notification, NotificationCalculator notificationCalculator) {
        System.out.println(notification.getType() + " price: " + notificationCalculator.calculatePrice(notification));
    }
}


interface Notification {
    String getType();

    Double getPricePerRecipient();

    Integer getRecipientsCount();
}

class NotificationCalculator {

    double calculatePrice(Notification notification) {
        return notification.getPricePerRecipient() * notification.getRecipientsCount();
    }
}


abstract class NotificationData {
    public int recipientsCount;
    public String message;

    public NotificationData(String message, int recipientsCount) {
        this.recipientsCount = recipientsCount;
        this.message = message;
    }


}

class EmailNotification extends NotificationData implements Notification {
    private static final String type = "Email";
    private static final double PRICE_PER_RECIPIENT = 0.1;


    public EmailNotification(String message, int recipientsCount) {
        super(message, recipientsCount);
    }

    @Override
    public Double getPricePerRecipient() {
        return PRICE_PER_RECIPIENT;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getRecipientsCount() {
        return recipientsCount;
    }
}

class SMSNotification extends NotificationData implements Notification {
    private static final String type = "SMS";
    public static final double PRICE_PER_RECIPIENT = 0.75;


    public SMSNotification(String message, int recipientsCount) {
        super(message, recipientsCount);
    }

    @Override
    public Double getPricePerRecipient() {
        return PRICE_PER_RECIPIENT;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getRecipientsCount() {
        return recipientsCount;
    }
}

class PushNotification extends NotificationData implements Notification {
    private static final String type = "Push";
    public static final double PRICE_PER_RECIPIENT = 0.25;
    private int recipientsCount;
    private String message;

    public PushNotification(String message, int recipientsCount) {
        super(message, recipientsCount);
    }

    @Override
    public Double getPricePerRecipient() {
        return PRICE_PER_RECIPIENT;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getRecipientsCount() {
        return recipientsCount;
    }
}

class TelegramNotification extends NotificationData implements Notification {
    private static final String type = "Telegram";
    public static final double PRICE_PER_RECIPIENT = 0.4;


    public TelegramNotification(String message, int recipientsCount) {
        super(message, recipientsCount);
    }

    @Override
    public Double getPricePerRecipient() {
        return PRICE_PER_RECIPIENT;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getRecipientsCount() {
        return recipientsCount;
    }
}

