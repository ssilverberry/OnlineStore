package com.company.store.model.dao;


import com.company.store.model.beans.Delivery;

import java.util.Collection;

public interface DeliveryDAO {

    Collection<Delivery> getAllDeliveries();
    Delivery getDeliveryById(int delivery_id);

    void saveDelivery(Delivery delivery);
    void updateStatus(int delivery_id, String status);
    void removeDelivery(int delivery_id);
}
