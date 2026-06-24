package com.hackaton.open_close_principe.good;

class TelegramNotification extends NotificationData {
    private static final String TYPE = "Telegram";
    public static final double PRICE_PER_RECIPIENT = 0.4;


    public TelegramNotification(String message, int recipientsCount) {
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
