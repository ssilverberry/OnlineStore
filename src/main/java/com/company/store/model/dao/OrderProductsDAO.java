package com.company.store.model.dao;

import com.company.store.model.beans.Order;
import com.company.store.model.beans.OrderProduct;
import com.company.store.model.beans.Product;

import java.util.Collection;

public interface OrderProductsDAO {

    Collection<OrderProduct> getOrderProductsById(int order_id);

    void saveProductToOrder(Product product, Order order, int amount, float price, boolean isUpdate);
    void removeProductFromOrder(int product_id, int order_id);
    void removeAllProductsFromOrder(int order_id);
}
