package com.company.store.model.dao;

import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;

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