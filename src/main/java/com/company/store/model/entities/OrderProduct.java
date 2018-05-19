package com.company.store.model.entities;

public class OrderProduct {

    private int order_id;
    private int product_id;
    private int amount;
    private float price;

    public OrderProduct() {
    }

    public OrderProduct(int order_id, int product_id, int amount, float price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.amount = amount;
        this.price = price;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderProduct: " +
                "\n order_id: " + order_id +
                "\n product_id: " + product_id +
                "\n amount: " + amount +
                "\n price: " + price;
    }
}
