package com.company.store.model.dao;



import com.company.store.model.beans.ProductAttribute;

import java.util.Collection;

public interface CategoryAttributeDAO {

    Collection<ProductAttribute> getAttributesForCategory(int product_id);
    ProductAttribute getAttributeById(int attr_id);

    void saveAttribute(ProductAttribute productAttribute);
    void removeAttribute(int attr_id);
}
