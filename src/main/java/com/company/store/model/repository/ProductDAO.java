package com.company.store.model.repository;


import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;

import java.util.Collection;
import java.util.Map;

public interface ProductDAO {

    Collection<Product> getCategories();
    Collection<Product> getSubcategories();
    Collection<Product> getProductsForCategory(int category_id);
    Product getProductById(int product_id);
    Product getProductByName(String name);
    Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id);

    // for admin
    boolean saveProduct(Product product);

    boolean removeProduct(int product_id);
    boolean removeCategoryProducts(int category_id);

}
