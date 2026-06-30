package com.patterns.factory_method.bad;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderNotificationService service = new OrderNotificationService();

        List<Order> orders = List.of(
                new Order("A-1001", "Alice", "alice@example.com", "email"),
                new Order("B-2450", "Bob", "+79001234567", "sms"),
                new Order("C-7322", "Charlie", "charlie-phone", "push")
        );

        for (Order order : orders) {
            service.notifyCustomer(order);
        }
    }
}

class OrderNotificationService {
    void notifyCustomer(Order order) {
        Notification notification;

        switch (order.channel()) {
            case "email" -> notification = new EmailNotification();
            case "sms" -> notification = new SmsNotification();
            case "push" -> notification = new PushNotification();
            default -> throw new IllegalArgumentException("Unknown channel: " + order.channel());
        }

        String message = "Hello, " + order.customerName() + "! Order " + order.id() + " is ready.";
        notification.send(order.recipient(), message);
    }
}

record Order(String id, String customerName, String recipient, String channel) {
}

interface Notification {
    void send(String recipient, String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("EMAIL to " + recipient + ": " + message);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS to " + recipient + ": " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("PUSH to " + recipient + ": " + message);
    }
}
