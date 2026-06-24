package com.hackaton.open_close_principe.good;

class SMSNotification extends NotificationData {
    private static final String TYPE = "SMS";
    public static final double PRICE_PER_RECIPIENT = 0.75;


    public SMSNotification(String message, int recipientsCount) {
        super(message, recipientsCount);
    }

    @Override
    public double getPricePerRecipient() {
        return PRICE_PER_RECIPIENT;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
