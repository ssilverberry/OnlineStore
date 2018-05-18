package com.yura.lampak.store.model.entities;


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

    public int getAttrId() {
        return attr_id;
    }

    public void setAttrId(int attr_id) {
        this.attr_id = attr_id;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
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
