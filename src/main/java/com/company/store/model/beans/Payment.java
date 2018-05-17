package com.company.store.model.beans;


public class Payment {

    private int payment_id;
    private int amount;
    private String type;
    private boolean isPaid;

    public Payment() {
    }

    public Payment(int payment_id, int amount, String type, boolean isPaid) {
        this.payment_id = payment_id;
        this.amount = amount;
        this.type = type;
        this.isPaid = isPaid;
    }

    public int getId() {
        return payment_id;
    }

    public void setId(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Payment: " +
                "\n payment_id: " + payment_id +
                "\n amount: " + amount +
                "\n type: " + type +
                "\n is paid: " + isPaid;
    }
}
