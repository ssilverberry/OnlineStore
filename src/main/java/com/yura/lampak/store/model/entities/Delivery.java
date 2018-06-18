package com.yura.lampak.store.model.entities;

public class Delivery {

    private int delivery_id;
    private String receiverName;
    private String receiverSurname;
    private String receiverPhone;
    private String address;
    private String status;

    public Delivery() {
    }

    public Delivery(int delivery_id, String receiverName, String receiverSurname, String receiverPhone, String address, String status) {
        this.delivery_id = delivery_id;
        this.receiverName = receiverName;
        this.receiverSurname = receiverSurname;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return delivery_id;
    }

    public void setId(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverSurname() {
        return receiverSurname;
    }

    public void setReceiverSurname(String receiverSurname) {
        this.receiverSurname = receiverSurname;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery: " +
                "\n delivery_id: " + delivery_id +
                "\n rec_name: " + receiverName +
                "\n rec surname: " + receiverSurname+
                "\n phone: " + receiverPhone +
                "\n address: " + address +
                "\n status: " + status;
    }
}
