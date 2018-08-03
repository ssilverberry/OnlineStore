package com.company.store.controller;

import com.company.store.entities.ProductParameter;
import com.company.store.repository.ProductParameterDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductParameterController {

    private ProductParameterDAO productParameterDAO;
    @Autowired
    public void setProductParameterDAO(ProductParameterDAO productParameterDAO) {
        this.productParameterDAO = productParameterDAO;
    }

    @RequestMapping(value = "saveparameter")
    public ModelAndView saveParameter(ProductParameter productParam, boolean isUpdate) {
        productParameterDAO.saveParameter(productParam, isUpdate);
        return new ModelAndView("saveupdate");
    }

    @RequestMapping(value = "removeparameterbyproductid")
    public ModelAndView removeParameterByProductId(@RequestParam("id") int id) {
        Boolean rem = productParameterDAO.removeParameterByProductId(id);
        if(rem){
        return new ModelAndView("removeparameterbyproductid");
    }else
        return new ModelAndView("notRemoveProduct");
    }
}
