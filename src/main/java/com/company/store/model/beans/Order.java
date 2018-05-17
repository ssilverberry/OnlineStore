package com.company.store.model.beans;


import java.sql.Timestamp;
import java.util.Collection;

public class Order {

    private int order_id;
    private Timestamp date;
    private int user_id;
    private int payment_id;
    private int delivery_id;
    private Collection products;

    public Order() {
    }

    public Order(int order_id, Timestamp date, int user_id, int payment_id, int delivery_id, Collection products) {
        this.order_id = order_id;
        this.date = date;
        this.user_id = user_id;
        this.payment_id = payment_id;
        this.delivery_id = delivery_id;
        this.products = products;
    }

    public int getId() {
        return order_id;
    }

    public void setId(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Collection getProducts() {
        return products;
    }

    public void setProducts(Collection products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order: " +
                "\n order_id: " + order_id +
                "\n date: " + date +
                "\n user_id: " + user_id +
                "\n payment_id: " + payment_id+
                "\n delivery_id: " + delivery_id;
    }
}
