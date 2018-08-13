package com.company.store.services.impl;

import com.company.store.forms.ProductObject;
import com.company.store.repository.CategoryAttributeDAO;
import com.company.store.repository.FeedbackDAO;
import com.company.store.repository.ProductDAO;
import com.company.store.repository.ProductParameterDAO;
import com.company.store.entities.Product;
import com.company.store.entities.ProductAttribute;
import com.company.store.entities.ProductParameter;
import com.company.store.forms.CategoryObject;
import com.company.store.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final CategoryAttributeDAO categoryAttributeDAO;
    private final FeedbackDAO feedbackDAO;
    private final ProductParameterDAO productParameterDAO;

    private static final String imgFilePath = "/resources/images/products/";

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, CategoryAttributeDAO categoryAttributeDAO,
                              FeedbackDAO feedbackDAO, ProductParameterDAO productParameterDAO) {
        this.productDAO = productDAO;
        this.categoryAttributeDAO = categoryAttributeDAO;
        this.feedbackDAO = feedbackDAO;
        this.productParameterDAO = productParameterDAO;
    }

    @Override
    public Map<Product, Collection<Product>> getCategories() {
        Collection<Product> mainCategories = productDAO.getCategories();
        Map<Product, Collection<Product>> categories = new TreeMap<>();
        mainCategories.forEach(product -> categories.put(product, productDAO.getProductsForCategory(product.getId())));
        return categories;
    }

    @Override
    public Map<Integer, String> getSubcategories(){
        Collection<Product> subcategories = productDAO.getSubcategories();
        Map<Integer, String> subcategMap = new TreeMap<>();
        subcategories.forEach(product -> subcategMap.put(product.getId(), product.getName()));
        return subcategMap;
    }

    @Override
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

    @Override
    public ModelMap getProductAndRating(int product_id){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("product", productDAO.getProductById(product_id));
        modelMap.addAttribute("rating", getProductRating(product_id));
        return modelMap;
    }

    public Product getProduct (int product_id){
        return productDAO.getProductById(product_id);
    }

    @Override
    public boolean updateProduct(Product product){
        product.setCategory(false);
        if (productDAO.saveProduct(product)){
            return productParameterDAO.updateParameters(product.getParams());
        } else return false;
    }

    @Override
    public boolean addProduct(ProductObject product){
        Product prodToSave = new Product(0, product.getParentId(), product.getName(), false);
        if (productDAO.saveProduct(prodToSave)){
            product.setId(productDAO.getProductByName(product.getName()).getId());
            saveProductParams(product.getParams(), product.getId());
            return saveProductImages(product.getFiles(), product.getAppPath(), product.getId());
        } else return false;
    }

    public Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id){
        return productDAO.getParamsForProduct(product_id);
    }

    private boolean saveProductParams(List<ProductParameter> productParams, int prod_id){
        productParams.forEach(productParameter -> productParameter.setProductId(prod_id));
        return productParameterDAO.saveParameters(productParams);
    }

    @Override
    public boolean deleteProduct(int prod_id){
        if (productParameterDAO.removeParameterByProductId(prod_id)){
            return productDAO.removeProduct(prod_id);
        }
        return false;
    }

    @Override
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

    @Override
    public boolean addCategory(CategoryObject object){
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

    @Override
    public boolean addSubcategory(CategoryObject object){
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

    @Override
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

    @Override
    public boolean updateSubcategory(CategoryObject object){
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
        attributes.forEach(attribute -> attribute.setProductId(subcategory.getId()));
        return categoryAttributeDAO.saveAttributes(attributes, false);
    }

    private boolean saveProductImages(MultipartFile[] imgs, String rootPath, int productId){
        if (saveProductImgsOnServer(imgs, rootPath)){
            return saveProductImgsToDB(productId, imgs);
        }
        return false;
    }

    private boolean saveProductImgsOnServer(MultipartFile[] imgs, String rootPath){
        for (MultipartFile file : imgs) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String dirName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
                File dir = new File(rootPath + imgFilePath + dirName);

                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File img = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(img));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private boolean saveProductImgsToDB(int productId, MultipartFile[] imgs){
        List<ProductParameter> productParameters = new ArrayList<>();
        int attr_id = categoryAttributeDAO.getAttributeByName("Main image").getAttrId();
        for (MultipartFile file: imgs) {
            ProductParameter parameter = new ProductParameter();
            parameter.setAttrId(attr_id);
            parameter.setProductId(productId);
            parameter.setValue(file.getOriginalFilename());
            productParameters.add(parameter);
        }
        return saveProductParams(productParameters, productId);
    }
}
