package com.company.store.entities;


import java.io.Serializable;

public class Feedback implements Serializable {
    
    private int feedback_id;
    private User user;
    private int product_id;
    private int rating;
    private String content;

    public Feedback() {
    }

    public Feedback(User user, int product_id, int rating, String content) {
        this.user = user;
        this.product_id = product_id;
        this.rating = rating;
        this.content = content;
    }

    public Feedback(User user, int product_id, String content) {
        this.user = user;
        this.product_id = product_id;
        this.content = content;
    }

    public Feedback(User user, int product_id, int rating) {
        this.user = user;
        this.product_id = product_id;
        this.rating = rating;
    }

    public int getId() {
        return feedback_id;
    }

    public void setId(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating_id) {
        this.rating = rating_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Feedback: " +
                "\n feed_id: " + feedback_id +
                "\n user: " + user.toString() +
                "\n prod_id: " + product_id+
                "\n rating: " + rating +
                "\n content: " + content;
    }
}
