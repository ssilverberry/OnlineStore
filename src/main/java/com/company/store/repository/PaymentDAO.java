package com.company.store.repository;

import com.company.store.entities.Payment;

public interface PaymentDAO {

    Payment getPaymentById(int payment_id);

    boolean savePayment(Payment payment);
    boolean removePayment(int payment_id);
}
