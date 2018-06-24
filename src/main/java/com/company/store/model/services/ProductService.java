package com.company.store.model.services;

import com.company.store.model.dao.CategoryAttributeDAO;
import com.company.store.model.dao.FeedbackDAO;
import com.company.store.model.dao.ProductDAO;
import com.company.store.model.dao.ProductParameterDAO;
import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.CategoryAttributeDAOImpl;
import com.company.store.model.impls.FeedbackDAOImpl;
import com.company.store.model.impls.ProductDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.function.Consumer;

@Component
public class ProductService {

    private final ProductDAO productDAO;
    private final CategoryAttributeDAO categoryAttributeDAO;
    private final FeedbackDAO feedbackDAO;
    private final ProductParameterDAO productParameterDAO;

    @Autowired
    public ProductService(ProductDAOImpl productDAO, CategoryAttributeDAOImpl categoryAttributeDAO,
                          FeedbackDAOImpl feedbackDAO, ProductParameterDAO productParameterDAO) {
        this.productDAO = productDAO;
        this.categoryAttributeDAO = categoryAttributeDAO;
        this.feedbackDAO = feedbackDAO;
        this.productParameterDAO = productParameterDAO;
    }

    public Map<Product, Collection<Product>> getCategories() {
        Collection<Product> mainCategories = productDAO.getCategories();
        Map<Product, Collection<Product>> categories = new TreeMap<>();
        mainCategories.forEach(new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                categories.put(product, productDAO.getProductsForCategory(product.getId()));
            }
        });
        return categories;
    }

    public Map<Integer, String> getSubcategories(){
        Collection<Product> subcategories = productDAO.getSubcategories();
        Map<Integer, String> subcategMap = new TreeMap<>();
        subcategories.forEach(new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                subcategMap.put(product.getId(), product.getName());
            }
        });
        return subcategMap;
    }

    public Collection<Product> getCategoryProducts(int category_id){
        return productDAO.getProductsForCategory(category_id);
    }

    public Collection<ProductAttribute> getCategoryFilters(int category_id){
        return categoryAttributeDAO.getAttributesForCategory(category_id);
    }

    private float getProductRating(int product_id){
        return feedbackDAO.getProductRating(product_id);
    }

    public ModelMap getProductById(int product_id){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("product", productDAO.getProductById(product_id));
        modelMap.addAttribute("rating", getProductRating(product_id));
        return modelMap;
    }

    public Product getProduct (int product_id){
        return productDAO.getProductById(product_id);
    }

    public boolean updateProduct(Product product){
        return productDAO.saveProduct(product);
    }

    public boolean addProduct(Product product){
        product.setId(0);
        product.getParams().forEach(new Consumer<ProductParameter>() {
            @Override
            public void accept(ProductParameter productParameter) {
                productParameterDAO.saveParameter(productParameter, false);
            }
        });
        return productDAO.saveProduct(product);
    }

    public List<ProductParameter> getProductParams(int product_id){
        return productParameterDAO.getProductParams(product_id);
    }

    public boolean saveProductParams(List<ProductParameter> productParams){
        return productParameterDAO.updateParameters(productParams);
    }

    public boolean saveProductParams(ProductParameter productParameter, boolean isUpdate){
        return productParameterDAO.saveParameter(productParameter, isUpdate);
    }

    public boolean deleteProduct(int prod_id){
        return productDAO.removeProduct(prod_id);
    }

}
