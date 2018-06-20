package com.company.store.model.services;

import com.company.store.model.dao.FeedbackDAO;
import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.impls.CategoryAttributeDAOImpl;
import com.company.store.model.impls.FeedbackDAOImpl;
import com.company.store.model.impls.ProductDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

@Component
public class ProductService {

    private final ProductDAOImpl productDAO;
    private final CategoryAttributeDAOImpl categoryAttributeDAO;
    private final FeedbackDAOImpl feedbackDAO;

    @Autowired
    public ProductService(ProductDAOImpl productDAO, CategoryAttributeDAOImpl categoryAttributeDAO, FeedbackDAOImpl feedbackDAO) {
        this.productDAO = productDAO;
        this.categoryAttributeDAO = categoryAttributeDAO;
        this.feedbackDAO = feedbackDAO;
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

    public Collection<Product> getCategoryProducts(int category_id){
        return productDAO.getProductsForCategory(category_id);
    }

    public Collection<ProductAttribute> getCategoryFilters(int category_id){
        return categoryAttributeDAO.getAttributesForCategory(category_id);
    }

    public float getProductRating(int product_id){
        return feedbackDAO.getProductRating(product_id);
    }

    public ModelMap getProductById(int product_id){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("product", productDAO.getProductById(product_id));
        modelMap.addAttribute("rating", getProductRating(product_id));
        return modelMap;
    }
}
