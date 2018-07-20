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

    public Collection<ProductAttribute> getCategoryAttrs(int category_id){
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
        product.setCategory(false);
        if (productDAO.saveProduct(product)){
            return productParameterDAO.updateParameters(product.getParams());
        } else return false;
    }

    public boolean addProduct(Product product){
        product.setId(0);
        if (productDAO.saveProduct(product)){
            product.setId(productDAO.getProductByName(product.getName()).getId());
            return saveProductParams(product.getParams(), product.getId());
        } else return false;
    }

    public List<ProductParameter> getProductParams(int product_id){
        return productParameterDAO.getProductParams(product_id);
    }

    private boolean saveProductParams(List<ProductParameter> productParams, int prod_id){
        productParams.forEach(productParameter -> {
            productParameter.setProductId(prod_id);
        });
        return productParameterDAO.saveParameters(productParams);
    }

    public boolean deleteProduct(int prod_id){
        return productDAO.removeProduct(prod_id);
    }

    public boolean deleteCategory(int categ__id){
        Collection<Product> subCategs = productDAO.getProductsForCategory(categ__id);
        if (subCategs != null && subCategs.size() > 0){
            subCategs.forEach(new Consumer<Product>() {
                @Override
                public void accept(Product product) {
                    productDAO.removeProduct(product.getId());
                }
            });
        }
        return productDAO.removeProduct(categ__id);
    }

    public boolean addCategory(Product product){
        product.setCategory(true);
        if(productDAO.saveProduct(product)){
            Product categ = productDAO.getProductByName(product.getName());
            List<ProductParameter> subCategs = product.getParams();
            subCategs.forEach(productParameter -> {
                Product subCateg = new Product();
                subCateg.setCategory(true);
                subCateg.setParentId(categ.getId());
                subCateg.setName(productParameter.getValue());
                productDAO.saveProduct(subCateg);
            });
            return true;
        } else return false;
    }
}
