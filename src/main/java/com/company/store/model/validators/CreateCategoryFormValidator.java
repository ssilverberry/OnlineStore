package com.company.store.model.validators;

import com.company.store.model.dao.ProductDAO;
import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;

@Component
public class CreateCategoryFormValidator implements Validator {


    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Product category = (Product) target;

        Collection<Product> existCategories = productDAO.getCategories();
        existCategories.addAll(productDAO.getSubcategories());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.category.name");

        for (Product prod: existCategories) {
            if (category.getName().equalsIgnoreCase(prod.getName())){
                errors.rejectValue("name", "Exist.category.params.value");
            }
        }

        for (int i = 0; i < category.getParams().size(); i++) {
            ProductParameter subcateg = category.getParams().get(i);

            if (subcateg == null || subcateg.getValue().equals("")){
                errors.rejectValue("params[" + i + "].value", "NotEmpty.category.params.value");
            } else {
                for (Product prod : existCategories) {
                    if (subcateg.getValue().equalsIgnoreCase(prod.getName())) {
                        errors.rejectValue("params[" + i + "].value", "Exist.category.params.value");
                    }
                }
            }
        }
    }
}
