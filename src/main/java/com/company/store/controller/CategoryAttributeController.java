package com.company.store.controller;

import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.impls.CategoryAttributeDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class CategoryAttributeController {

    private CategoryAttributeDAOImpl categoryAttributeDAO;

    @RequestMapping("/attributesforcategory/{category_id}")
    public ModelAndView getAttributesForCategory(@PathVariable int category_id) {
        Collection<ProductAttribute> attributes = categoryAttributeDAO.getAttributesForCategory(category_id);
        return new ModelAndView("attributesforcategory", "attributes", attributes);
    }

    @RequestMapping("/attributebyid/{attr_id}")
    public ModelAndView getAttributeById(@PathVariable int attr_id) {
        categoryAttributeDAO.getAttributeById(attr_id);
        return new ModelAndView("attr_id");
    }

    @RequestMapping("/saveattribute/{attribute}")
    public ModelAndView saveAttribute(ProductAttribute attribute) {
        categoryAttributeDAO.saveAttribute(attribute);
        return new ModelAndView("saveattribute");
    }

    @RequestMapping("/removeattribute/{attr_id}")
    public ModelAndView removeAttribute(@PathVariable int attr_id) {
        categoryAttributeDAO.removeAttribute(attr_id);
        return new ModelAndView("removeattr_id");
    }
}
