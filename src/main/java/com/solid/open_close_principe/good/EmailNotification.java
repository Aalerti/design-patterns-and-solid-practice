package com.solid.open_close_principe.good;

class EmailNotification extends NotificationData {
    private static final String TYPE = "Email";
    private static final double PRICE_PER_RECIPIENT = 0.1;


    public EmailNotification(String message, int recipientsCount) {
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
