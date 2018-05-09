package com.yura.lampak.store.model.dao;


import com.yura.lampak.store.model.beans.Product;
import com.yura.lampak.store.model.beans.ProductAttribute;
import com.yura.lampak.store.model.beans.ProductParameter;

import java.util.Collection;
import java.util.Map;

public interface ProductDAO {

    Collection<Product> getCategories();
    Collection<Product> getProductsForCategory(int category_id);
    Product getProductById(int product_id);
    Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id);

    // for admin
    void saveProduct(Product product);
    void removeProduct(int product_id);
}
