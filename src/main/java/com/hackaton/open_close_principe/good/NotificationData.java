package com.hackaton.open_close_principe.good;

abstract class NotificationData implements Notification {
    private final int recipientsCount;
    private final String message;

    public NotificationData(String message, int recipientsCount) {
        this.recipientsCount = recipientsCount;
        this.message = message;
    }

    @Override
    public int getRecipientsCount() {
        return recipientsCount;
    }
}
