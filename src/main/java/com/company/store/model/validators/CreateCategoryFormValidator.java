package com.company.store.model.validators;

import com.company.store.model.dao.ProductDAO;
import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.category.name");

        Collection<Product> existCategories = productDAO.getCategories();
        existCategories.addAll(productDAO.getSubcategories());

        trimNames(category);

        checkOnCommonCategories(category, errors);

        checkOnExistCategories(category, errors, existCategories);

    }

    private void checkOnCommonCategories(Product category, Errors errors){
        List<Integer> commonIndexes = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < category.getParams().size(); i++) {
            ProductParameter subcategory = category.getParams().get(i);
            if (subcategory == null || subcategory.getValue().equals("")){
                errors.rejectValue("params[" + i + "].value", "NotEmpty.category.params.value");
            } else if (category.getName().equalsIgnoreCase(subcategory.getValue())){
                if (!flag) {
                    errors.rejectValue("name", "Identity.category.name");
                    flag = true;
                }
                if (!commonIndexes.contains(i)) {
                    commonIndexes.add(i);
                    errors.rejectValue("params[" + i + "].value", "Identity.category.params.value");
                }
            }
            else {
                for (int j = 0; j < category.getParams().size(); j++){
                    if (i != j){
                        ProductParameter compareSub = category.getParams().get(j);
                        if (subcategory.getValue().equalsIgnoreCase(compareSub.getValue())){
                            if (!commonIndexes.contains(j)) {
                                commonIndexes.add(j);
                                errors.rejectValue("params[" + j + "].value", "Identity.category.params.value");
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkOnExistCategories(Product category, Errors errors, Collection<Product> existCategories){

        for (int i = 0; i < category.getParams().size(); i++) {
            ProductParameter subcateg = category.getParams().get(i);
            for (Product prod : existCategories) {
                if (category.getName().equalsIgnoreCase(prod.getName())){
                    errors.rejectValue("name", "Exist.category.params.value");
                }
                if (subcateg.getValue().equalsIgnoreCase(prod.getName())) {
                    errors.rejectValue("params[" + i + "].value", "Exist.category.params.value");
                }
            }
        }

    }

    private void trimNames(Product category){
        category.setName(category.getName().trim());
        for (int i = 0; i < category.getParams().size(); i++) {
            ProductParameter subcateg = category.getParams().get(i);
            if (subcateg != null){
                subcateg.setValue(subcateg.getValue().trim());
                category.getParams().set(i, subcateg);
            }
        }
    }
}
