package com.yura.lampak.store.model.dao;


import com.yura.lampak.store.model.entities.Order;

import java.util.Collection;

public interface OrderDAO {

    Collection<Order> getAllOrders();
    Order getOrderById(int order_id);
    Collection<Order> getAllOrdersForUser(int user_id);

    void saveOrder(Order order);
    void removeOrderById(int order_id);
}
