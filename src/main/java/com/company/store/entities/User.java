package com.company.store.entities;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

public class User {

    private int user_id;
    private String name;
    private String surname;
    @Email
    private String email;
    private String phone;
    @Size(min = 6, max = 15)
    private String password;
    private String address;
    private boolean isAdmin;
    private UserRoles role;

    private User() {
    }

    public static UserBuilder newBuilder() {
        return new User().new UserBuilder();
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
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

    public class UserBuilder {

        private UserBuilder() {
        }

        public UserBuilder setRole(UserRoles role) {
            User.this.setRole(role);
            return this;
        }

        public UserBuilder setId(int id) {
            User.this.setId(id);
            return this;
        }

        public UserBuilder setName(String name) {
            User.this.setName(name);
            return this;
        }

        public UserBuilder setSurname(String surname) {
            User.this.setSurname(surname);
            return this;
        }

        public UserBuilder setEmail(String email) {
            User.this.setEmail(email);
            return this;
        }

        public UserBuilder setPhone(String phone) {
            User.this.setPhone(phone);
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.setPassword(password);
            return this;
        }

        public UserBuilder setAddress(String address) {
            User.this.setAddress(address);
            return this;
        }

        public UserBuilder setAdmin(boolean isAdmin) {
            User.this.setAdmin(isAdmin);
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
