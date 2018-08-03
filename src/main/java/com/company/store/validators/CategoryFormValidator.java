package com.company.store.validators;

import com.company.store.repository.ProductDAO;
import com.company.store.entities.Product;
import com.company.store.entities.ProductAttribute;
import com.company.store.forms.CategoryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CategoryFormValidator implements Validator {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryObject.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CategoryObject category = (CategoryObject) target;

        trimNames(category);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "NotEmpty.category.categoryName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subcategoryName", "NotEmpty.category.subcategoryName");

        Collection<Product> existCategories = productDAO.getCategories();
        existCategories.addAll(productDAO.getSubcategories());

        checkOnCommonNames(category, errors);

        checkOnExistingCategories(category, errors, existCategories);

    }

    private void checkOnCommonNames(CategoryObject category, Errors errors){
        if (category.getCategoryName().equalsIgnoreCase(category.getSubcategoryName()) && !category.getCategoryName().equals("")){
            errors.rejectValue("categoryName", "Identity.category.categoryName");
            errors.rejectValue("subcategoryName", "Identity.category.subcategoryName");
        }

        List<Integer> commonIndexes = new ArrayList<>();
        for (int i = 0; i < category.getAttributes().size(); i++) {
            ProductAttribute attribute = category.getAttributes().get(i);
            if (attribute == null || attribute.getName().equals("")){
                errors.rejectValue("attributes[" + i + "].name", "NotEmpty.category.attributes.name");
            }
            else {
                for (int j = 0; j < category.getAttributes().size(); j++){
                    if (i != j){
                        ProductAttribute attrToCompare = category.getAttributes().get(j);
                        if (attribute.getName().equalsIgnoreCase(attrToCompare.getName()) && !attrToCompare.getName().equals("")){
                            if (!commonIndexes.contains(j)) {
                                commonIndexes.add(j);
                                errors.rejectValue("attributes[" + j + "].name", "Identity.category.attributes.name");
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkOnExistingCategories(CategoryObject object, Errors errors, Collection<Product> existCategories){
        if (!object.isUpdate()){
            for (Product categ: existCategories) {
                if (object.getCategoryName().equalsIgnoreCase(categ.getName())){
                    errors.rejectValue("categoryName", "Exist.category.categoryName");
                }
                if (object.getSubcategoryName().equalsIgnoreCase(categ.getName())){
                    errors.rejectValue("subcategoryName", "Exist.category.subcategoryName");
                }
            }
        }
        if (object.getCategoryId() != null && object.getCategoryId() != 0){
            for (Product categ: existCategories) {
                if (object.getSubcategoryName().equalsIgnoreCase(categ.getName())){
                    errors.rejectValue("subcategoryName", "Exist.category.subcategoryName");
                }
            }
        }
    }

    private void trimNames(CategoryObject category){
        category.setCategoryName(category.getCategoryName().trim());
        category.setSubcategoryName(category.getSubcategoryName().trim());
        for (int i = 0; i < category.getAttributes().size(); i++) {
            ProductAttribute attribute = category.getAttributes().get(i);
            if (attribute != null){
                System.out.println("IN trimNames attr_id " + attribute.toString());
                attribute.setName(attribute.getName().trim());
                category.getAttributes().set(i, attribute);
            }
        }
    }
}
