package com.company.store.controller;

import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductParameterDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class ProductParameterController {

    private ProductParameterDAOImpl productParameterDAO;

    public void setProductParameterDAO(ProductParameterDAOImpl productParameterDAO) {
        this.productParameterDAO = productParameterDAO;
    }

    @RequestMapping(value = "parambyproductandattrids")
    public ModelAndView getParamByProductAndAttrIds(@RequestParam(value = "id") String id,
                                                    @RequestParam (value = "attr_id") String attr_id) {
        ProductParameter productParameter =
                productParameterDAO.getParamByProductAndAttrIds(Integer.parseInt(id), Integer.parseInt(id));
        return new ModelAndView("productparamandattribs",
                "productParameter", productParameter);
    }

    @RequestMapping(value = "saveparameter") // not have solution fo this boolean variable & object
    public ModelAndView saveParameter(ProductParameter productParam, boolean isUpdate) {
        productParameterDAO.saveParameter(productParam, isUpdate);
        return new ModelAndView("saveupdate");
    }

    @RequestMapping(value = "removeparameterbyproductid")
    public ModelAndView removeParameterByProductId(@RequestParam("id") int id) {
        Boolean rem = productParameterDAO.removeParameterByProductId(id);
        if(rem == true){
        return new ModelAndView("removeparameterbyproductid");
    }else
        return new ModelAndView("notRemove");
    }
}
