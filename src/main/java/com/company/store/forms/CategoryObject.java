package com.company.store.forms;

import com.company.store.entities.ProductAttribute;

import java.util.List;

public class CategoryObject {

    private Integer categoryId;
    private Integer subcategoryId;
    private String categoryName;
    private String subcategoryName;
    private List<ProductAttribute> attributes;
    private boolean isUpdate;

    public CategoryObject() {
    }

    public CategoryObject(Integer categoryId, String categoryName, String subcategoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

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

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }
}
