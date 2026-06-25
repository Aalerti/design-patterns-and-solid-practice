package com.solid.open_close_principe.good;

class PushNotification extends NotificationData  {
    private static final String TYPE = "Push";
    public static final double PRICE_PER_RECIPIENT = 0.25;


    public PushNotification(String message, int recipientsCount) {
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
