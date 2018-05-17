package com.yura.lampak.store.model.dao;


import com.yura.lampak.store.model.entities.Payment;

public interface PaymentDAO {

    Payment getPaymentById(int payment_id);

    void savePayment(Payment payment);
    void removePayment(int payment_id);
}
