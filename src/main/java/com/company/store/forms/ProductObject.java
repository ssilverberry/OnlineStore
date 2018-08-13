package com.company.store.forms;

import com.company.store.entities.ProductAttribute;
import com.company.store.entities.ProductParameter;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class ProductObject {

    private int productId;
    private int parentId;
    private String name;
    private Map<ProductAttribute, ProductParameter> parameters;
    private List<ProductParameter> params;
    private boolean isCategory;
    private MultipartFile[] files;
    private String appPath;

    public int getId() {
        return productId;
    }

    public void setId(int productId) {
        this.productId = productId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<ProductAttribute, ProductParameter> getParameters() {
        return parameters;
    }

    public void setParameters(Map<ProductAttribute, ProductParameter> parameters) {
        this.parameters = parameters;
    }

    public List<ProductParameter> getParams() {
        return params;
    }

    public void setParams(List<ProductParameter> params) {
        this.params = params;
    }

    public boolean isCategory() {
        return isCategory;
    }

    public void setCategory(boolean category) {
        isCategory = category;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }
}
