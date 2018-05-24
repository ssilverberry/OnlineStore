package com.company.store.controller;

import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductParameterDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class ProductParameterController {

    private ProductParameterDAOImpl productParameterDAO;

    public void setProductParameterDAO(ProductParameterDAOImpl productParameterDAO) {
        this.productParameterDAO = productParameterDAO;
    }

    @RequestMapping(value = "parambyproductandattrids/{id}/attr/{id}")
    public ModelAndView getParamByProductAndAttrIds(@PathVariable("id") String id) {
        ProductParameter productParameter = productParameterDAO.getParamByProductAndAttrIds(Integer.parseInt(id), Integer.parseInt(id));
        return new ModelAndView("productparamandattribs", "productParameter", productParameter);
    }

    @RequestMapping(value = "saveparameter")
    public ModelAndView saveParameter(ProductParameter productParam, boolean isUpdate) {
        productParameterDAO.saveParameter(productParam, isUpdate);
        return new ModelAndView("saveupdate");
    }

    @RequestMapping(value = "removeparameterbyproductid/{id}")
    public ModelAndView removeParameterByProductId(@PathVariable("id") String id) {
        productParameterDAO.removeParameterByProductId(Integer.parseInt(id));
        return new ModelAndView("removeparameterbyproductid");
    }
}
