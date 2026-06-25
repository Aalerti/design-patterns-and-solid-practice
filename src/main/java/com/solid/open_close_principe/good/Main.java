package com.solid.open_close_principe.good;

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
