package com.yura.lampak.store.model.entities;


public class User {

    private int user_id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String address;
    private boolean isAdmin;

    public User() {
    }

    public User(int user_id, String name, String surname, String email, String phone, String password, String address,
                boolean isAdmin) {
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User: " +
                "\n user_id: " + user_id +
                "\n name: " + name +
                "\n surname: " + surname +
                "\n email: " + email +
                "\n phone: " + phone +
                "\n address: " + address +
                "\n is admin: " + isAdmin;
    }
}
