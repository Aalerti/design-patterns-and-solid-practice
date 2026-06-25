package com.solid.open_close_principe.good;

interface Notification {
    String getType();

    double getPricePerRecipient();

    int getRecipientsCount();
}
