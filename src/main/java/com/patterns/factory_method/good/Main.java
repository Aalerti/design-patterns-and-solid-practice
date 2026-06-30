package com.patterns.factory_method.good;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderNotificationFactory smsFactory = new OrderSMSNotificationFactory();
        OrderNotificationFactory emailFactory = new OrderEmailNotificationFactory();
        OrderNotificationFactory pushFactory = new OrderPushNotificationFactory();

        List<Order> orders = List.of(
                new Order("A-1001", "Alice", "alice@example.com", emailFactory),
                new Order("B-2450", "Bob", "+79001234567", smsFactory),
                new Order("C-7322", "Charlie", "charlie-phone", pushFactory)
        );

        for (Order order : orders) {
            order.factory().notifyCustomer(order);
        }
    }
}

abstract class OrderNotificationFactory {
    void notifyCustomer(Order order) {
        Notification notification = createNotification();
        String message = "Hello, " + order.customerName() + "! Order " + order.id() + " is ready.";
        notification.send(order.recipient(), message);
    }

    protected abstract Notification createNotification();
}

class OrderSMSNotificationFactory extends OrderNotificationFactory {
    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}

class OrderEmailNotificationFactory extends OrderNotificationFactory {
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}

class OrderPushNotificationFactory extends OrderNotificationFactory {
    @Override
    protected Notification createNotification() {
        return new PushNotification();
    }
}


record Order(String id, String customerName, String recipient, OrderNotificationFactory factory) {
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
