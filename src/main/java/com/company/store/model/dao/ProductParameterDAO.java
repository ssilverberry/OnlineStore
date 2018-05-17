package com.company.store.model.dao;

import com.company.store.model.entities.ProductParameter;

public interface ProductParameterDAO {

    ProductParameter getParamByProductAndAttrIds(int product_id, int attr_id);

//    for admin
    void saveParameter(ProductParameter productParam, boolean isUpdate);
    void removeParameterByProductId(int product_id);
}
