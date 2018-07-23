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

    public Product getProductByName(String productName){
        return productDAO.getProductByName(productName);
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

    public boolean addSubcategory(CategoryFormObject object){
        Product subcategory = new Product();
        subcategory.setName(object.getSubcategoryName());
        subcategory.setCategory(true);
        subcategory.setParentId(productDAO.getProductById(object.getCategoryId()).getId());
        if (productDAO.saveProduct(subcategory)){
            subcategory.setId(productDAO.getProductByName(subcategory.getName()).getId());
            return saveCategoryAttributes(subcategory, object.getAttributes());
        }
        return false;
    }

    public boolean deleteSubcategory(int subcategory_id){
        boolean result;
        if (deleteCategoryProducts(subcategory_id)){
            result = categoryAttributeDAO.removeCategoryAttributes(subcategory_id);
            if (!result){
                return false;
            }
        }
        return productDAO.removeProduct(subcategory_id);
    }

    public boolean updateSubcategory(CategoryFormObject object){
        Product subcategory = new Product();
        subcategory.setId(object.getSubcategoryId());
        subcategory.setName(object.getSubcategoryName());
        subcategory.setParentId(productDAO.getProductByName(object.getCategoryName()).getId());
        subcategory.setCategory(true);
        if (productDAO.saveProduct(subcategory)){
            updateAttributes(subcategory.getId(), object.getAttributes());
        }
        return false;
    }

    private void updateAttributes(int category_id, List<ProductAttribute> attributes){
        List<ProductAttribute> existingAttrs = categoryAttributeDAO.getAttributesForCategory(category_id);
        boolean isExist;
        checkForNewAttrs(attributes, category_id);
        for (ProductAttribute existAttr : existingAttrs) {
            isExist = false;
            for (ProductAttribute attribute : attributes){
                attribute.setProductId(category_id);
                if (existAttr.getAttrId() == attribute.getAttrId()){
                    System.out.println("IN updateAttributes: attr to save " + attribute.toString());
                    categoryAttributeDAO.saveAttribute(attribute);
                    isExist = true;
                }
            }
            if (!isExist){
                Collection<Product> categoryProducts = productDAO.getProductsForCategory(category_id);
                if (categoryProducts.size() > 0){
                    for (Product product : categoryProducts) {
                       List<ProductParameter> params = productParameterDAO.getProductParams(product.getId());
                        for (ProductParameter param : params) {
                            if (existAttr.getAttrId() == param.getAttrId()){
                                productParameterDAO.removeParameterByAttrId(existAttr.getAttrId());
                            }
                        }
                    }
                }
                categoryAttributeDAO.removeAttribute(existAttr.getAttrId());
            }
        }
    }

    private void checkForNewAttrs(List<ProductAttribute> attributes, int category_id){
        for (ProductAttribute attribute : attributes){
            attribute.setProductId(category_id);
            if (attribute.getAttrId() == 0){
                categoryAttributeDAO.saveAttribute(attribute);
                int attr_id = categoryAttributeDAO.getAttributeByName(attribute.getName()).getAttrId();
                Collection<Product> products = productDAO.getProductsForCategory(category_id);
                List<ProductParameter> paramsToAdd = new ArrayList<>();
                for (Product product : products) {
                    paramsToAdd.add(new ProductParameter(attr_id, product.getId(), " "));
                }
                productParameterDAO.saveParameters(paramsToAdd);
            }
        }
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
