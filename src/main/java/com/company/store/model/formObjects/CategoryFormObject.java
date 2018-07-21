package com.company.store.model.formObjects;

import com.company.store.model.entities.ProductAttribute;

import java.util.List;

public class CategoryFormObject {

    private String categoryName;
    private String subcategoryName;
    private List<ProductAttribute> attributes;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public List<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttribute> attributes) {
        this.attributes = attributes;
    }
}
