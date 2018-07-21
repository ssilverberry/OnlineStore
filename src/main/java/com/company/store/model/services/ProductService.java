package com.company.store.model.services;

import com.company.store.model.dao.CategoryAttributeDAO;
import com.company.store.model.dao.FeedbackDAO;
import com.company.store.model.dao.ProductDAO;
import com.company.store.model.dao.ProductParameterDAO;
import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.formObjects.CategoryFormObject;
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

    public List<ProductAttribute> getCategoryAttrs(int category_id){
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

    public Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id){
        return productDAO.getParamsForProduct(product_id);
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
        if (productParameterDAO.removeParameterByProductId(prod_id)){
            return productDAO.removeProduct(prod_id);
        }
        return false;
    }

    public boolean deleteCategory(int category_id){
        Collection<Product> subcategories = productDAO.getProductsForCategory(category_id);
        boolean result;
        for (Product subcategory: subcategories) {
            if (deleteCategoryProducts(subcategory.getId())){
                result = categoryAttributeDAO.removeCategoryAttributes(subcategory.getId());
                if (!result){
                    return false;
                }
            }
        }
        if (productDAO.removeCategoryProducts(category_id)){
            return productDAO.removeProduct(category_id);
        }
        return false;
    }

    private boolean deleteCategoryProducts(int subcategory_id){
        boolean resultFlag = true;
        Collection<Product> products = productDAO.getProductsForCategory(subcategory_id);
        for (Product product: products) {
            resultFlag = deleteProduct(product.getId());
            if (!resultFlag) {
                break;
            }
        }
        return resultFlag;
    }

    public boolean addCategory(CategoryFormObject object){
        Product category = new Product();
        category.setName(object.getCategoryName());
        category.setCategory(true);
        if(productDAO.saveProduct(category)){
            category.setId(productDAO.getProductByName(category.getName()).getId());
            Product subcategory = new Product();
            subcategory.setName(object.getSubcategoryName());
            subcategory.setCategory(true);
            subcategory.setParentId(category.getId());
            if (productDAO.saveProduct(subcategory)){
                subcategory.setId(productDAO.getProductByName(subcategory.getName()).getId());
                return saveCategoryAttributes(subcategory, object.getAttributes());
            }
        }
        return false;
    }

    public boolean updateSubcategory(CategoryFormObject object){
        Product subcategory = new Product();
        subcategory.setId(object.getSubcategoryId());
        subcategory.setName(object.getSubcategoryName());
        subcategory.setParentId(productDAO.getProductByName(object.getCategoryName()).getId());
        subcategory.setCategory(true);
        if (productDAO.saveProduct(subcategory)){
            for (ProductAttribute attr: object.getAttributes()) {
                System.out.println("IN updateSubcategory attr_id " + attr.getAttrId());
                attr.setProductId(subcategory.getId());
                categoryAttributeDAO.saveAttribute(attr);
            }
            //return categoryAttributeDAO.saveAttributes(object.getAttributes(), true);
        }
        return false;
    }

    private boolean saveCategoryAttributes(Product subcategory, List<ProductAttribute> attributes){
        attributes.forEach(new Consumer<ProductAttribute>() {
            @Override
            public void accept(ProductAttribute attribute) {
                attribute.setProductId(subcategory.getId());
            }
        });
        return categoryAttributeDAO.saveAttributes(attributes, false);
    }
}
