package com.company.store.model.entities;


public class ProductParameter {

    private int attr_id;
    private int product_id;
    private String value;

    public ProductParameter() {
    }

    public ProductParameter(int attr_id, int product_id, String value) {
        this.attr_id = attr_id;
        this.product_id = product_id;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductParam: " +
                "\n product_id: " + product_id +
                "\n attr_id: " + attr_id +
                "\n value: " + value;
    }
}
