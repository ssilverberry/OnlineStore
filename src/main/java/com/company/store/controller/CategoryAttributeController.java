package com.company.store.controller;

import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.impls.CategoryAttributeDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.stream.events.Attribute;
import java.util.Collection;
import java.util.Locale;

@Controller
public class CategoryAttributeController {

    private CategoryAttributeDAOImpl categoryAttributeDAO;

    public void setCategoryAttributeDAO(CategoryAttributeDAOImpl categoryAttributeDAO) {
        this.categoryAttributeDAO = categoryAttributeDAO;
    }

    @RequestMapping(value = "attributesforcategory/{id}", method = RequestMethod.GET)
    public ModelAndView getAttributesForCategory(@PathVariable("id") String id) {
        Collection<ProductAttribute> attributes = categoryAttributeDAO.getAttributesForCategory(Integer.parseInt(id));
        return new ModelAndView("attributesforcategory", "attributes", attributes);
    }

    @RequestMapping(value = "attributebyid/{id}", method = RequestMethod.GET)
    public ModelAndView getAttributeById(@PathVariable("id") String id) {
        categoryAttributeDAO.getAttributeById(Integer.parseInt(id));
        return new ModelAndView("attr_id");
    }

    @RequestMapping(value = "saveattribute", method = RequestMethod.POST)
    public ModelAndView saveAttribute(ProductAttribute attribute) {
        categoryAttributeDAO.saveAttribute(attribute);
        return new ModelAndView("saveattribute");
    }

    @RequestMapping(value = "/removeattribute/{id}", method = RequestMethod.POST)
    public ModelAndView removeAttribute(@PathVariable("id") String id) {
        categoryAttributeDAO.removeAttribute(Integer.parseInt(id));
        return new ModelAndView("removeattr_id");
    }
}
