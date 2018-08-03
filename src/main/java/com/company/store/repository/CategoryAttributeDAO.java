package com.company.store.repository;


import com.company.store.entities.ProductAttribute;

import java.util.List;

public interface CategoryAttributeDAO {

    List<ProductAttribute> getAttributesForCategory(int product_id);
    ProductAttribute getAttributeById(int attr_id);
    ProductAttribute getAttributeByName(String attrName);

    boolean saveAttribute(ProductAttribute productAttribute);
    boolean saveAttributes(List<ProductAttribute> attributes, boolean isUpdate);

    boolean removeAttribute(int attr_id);
    boolean removeCategoryAttributes(int product_id);
}
