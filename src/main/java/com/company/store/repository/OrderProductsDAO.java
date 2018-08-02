package com.company.store.repository;

import com.company.store.entities.Order;
import com.company.store.entities.OrderProduct;

import java.util.Collection;

public interface OrderProductsDAO {

    Collection<OrderProduct> getOrderProductsById(int order_id);

    boolean saveProductToOrder(int product_id, Order order, int amount, float price, boolean isUpdate);
    boolean removeProductFromOrder(int product_id, int order_id);
    boolean removeAllProductsFromOrder(int order_id);
}
