package com.company.store.model.beans;


public class ProductAttribute {

    private int attr_id;
    private int product_id;
    private String name;

    public ProductAttribute() {
    }

    public ProductAttribute(int attr_id, int product_id, String name) {
        this.attr_id = attr_id;
        this.product_id = product_id;
        this.name = name;
    }

    public int getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(int attr_id) {
        this.attr_id = attr_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductAttr: " +
                "\n product_id: " + product_id +
                "\n attr_id: " + attr_id +
                "\n name: " + name;
    }
}
