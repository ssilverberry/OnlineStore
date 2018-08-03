package com.company.store.services;

import com.company.store.entities.Product;
import com.company.store.entities.ProductAttribute;
import com.company.store.entities.ProductParameter;
import com.company.store.forms.CategoryObject;
import org.springframework.ui.ModelMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<Product, Collection<Product>> getCategories();

    Map<Integer, String> getSubcategories();

    Collection<Product> getCategoryProducts(int category_id);

    List<ProductAttribute> getCategoryAttrs(int category_id);

    Product getProductByName(String productName);

    Product getProduct (int product_id);

    ModelMap getProductAndRating(int product_id);

    Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id);

    boolean updateProduct(Product product);

    boolean addProduct(Product product);

    boolean deleteProduct(int prod_id);

    boolean deleteCategory(int category_id);

    boolean addCategory(CategoryObject object);

    boolean addSubcategory(CategoryObject object);

    boolean deleteSubcategory(int subcategory_id);

    boolean updateSubcategory(CategoryObject object);

}
