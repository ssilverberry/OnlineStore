package com.yura.lampak.store.model.dao;

import com.yura.lampak.store.model.entities.Delivery;

import java.util.Collection;

public interface DeliveryDAO {

    Collection<Delivery> getAllDeliveries();
    Delivery getDeliveryById(int delivery_id);

    void saveDelivery(Delivery delivery);
    void updateStatus(int delivery_id, String status);
    void removeDelivery(int delivery_id);
}
