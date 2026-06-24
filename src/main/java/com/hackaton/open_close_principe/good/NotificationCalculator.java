package com.hackaton.open_close_principe.good;

class NotificationCalculator {

    double calculatePrice(Notification notification) {
        return notification.getPricePerRecipient() * notification.getRecipientsCount();
    }
}
