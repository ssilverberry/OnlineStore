package com.company.store.controller;

import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductParameterDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductParameterController {

    private ProductParameterDAOImpl productParameterDAO;

    @RequestMapping(value = "/parambyproductandattrids/{product_id}/attr/{attr_id}")
    public ModelAndView getParamByProductAndAttrIds(@PathVariable int product_id, int attr_id) {
        productParameterDAO.getParamByProductAndAttrIds(product_id, attr_id);
        return new ModelAndView("/productparamandattribs");
    }

    @RequestMapping(value = "/saveparameter/{productParam}")
    public ModelAndView saveParameter(ProductParameter productParam, boolean isUpdate) {
        productParameterDAO.saveParameter(productParam, isUpdate);
        return new ModelAndView("saveupdate");
    }

    @RequestMapping(value = "/removeparameterbyproductid/{product_id}")
    public ModelAndView removeParameterByProductId(@PathVariable int product_id) {
        productParameterDAO.removeParameterByProductId(product_id);
        return new ModelAndView("removeparameterbyproductid");
    }
}
