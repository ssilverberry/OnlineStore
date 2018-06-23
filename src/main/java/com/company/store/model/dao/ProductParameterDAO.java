package com.company.store.model.dao;


import com.company.store.model.entities.ProductParameter;

import java.util.Collection;
import java.util.List;

public interface ProductParameterDAO {

    //ProductParameter getParamByProductAndAttrIds(int product_id, int attr_id);
    List<ProductParameter> getProductParams(int product_id);

//    for admin
    boolean saveParameter(ProductParameter productParam, boolean isUpdate);
    boolean saveParameters(List<ProductParameter> productParams, boolean isUpdate);
    boolean removeParameterByProductId(int product_id);
}
