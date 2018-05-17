package com.company.store.model.beans;

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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
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
