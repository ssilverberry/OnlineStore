package com.company.store.validators;

import com.company.store.forms.ProductObject;
import com.company.store.repository.ProductDAO;
import com.company.store.entities.Product;
import com.company.store.entities.ProductParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductFormValidator implements Validator {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductObject.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProductObject product = (ProductObject) target;

        checkProductName(product, errors);

        checkParameters(product, errors);

        checkProductImgs(product, errors);
    }

    private void checkProductName(ProductObject product, Errors errors){
        String productName = product.getName().trim();
        if (productName.equals("")){
            errors.rejectValue("name", "NotEmpty.product.name");
        }
        if (product.getId() == 0){
            Product prodDB = productDAO.getProductByName(productName);
            if (prodDB != null){
                errors.rejectValue("name", "Exist.product.name");
            }
        }
    }

    private void checkParameters(ProductObject product, Errors errors){
        List<Integer> commonIndexes = new ArrayList<>();
        for (int i = 0; i < product.getParams().size(); i++) {
            ProductParameter parameter = product.getParams().get(i);
            if (parameter != null){
                parameter.setValue(parameter.getValue().trim());
                product.getParams().set(i, parameter);
            }
            if (parameter == null || parameter.getValue().equalsIgnoreCase("")){
                errors.rejectValue("params[" + i + "].value", "NotEmpty.product.params.value");
            } else {
                for (int j = 0; j < product.getParams().size(); j++){
                    if (i != j){
                        ProductParameter paramToCompare = product.getParams().get(j);
                        if (parameter.getValue().equalsIgnoreCase(paramToCompare.getValue())){
                            if (!commonIndexes.contains(j)) {
                                commonIndexes.add(j);
                                errors.rejectValue("params[" + j + "].value", "Identity.product.params.value");
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkProductImgs(ProductObject product, Errors errors){
        for (MultipartFile file: product.getFiles()) {
            if (file.getOriginalFilename().equals("")){
                errors.rejectValue("files", "NotEmpty.product.files");
            }
        }
    }

}
