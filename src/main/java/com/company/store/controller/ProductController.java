package com.company.store.controller;


import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


@Controller
public class ProductController {

    private final ProductDAOImpl productDAO;

    @Autowired
    public ProductController(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showCategories() {
        Collection<Product> categoryList = productDAO.getCategories();
        categoryList.forEach(product -> System.out.println(product.toString()));
        return new ModelAndView("index", "categoryList", categoryList);
    }

    @RequestMapping(value = "productCategoriesId")
    public ModelAndView showProductForCategory(@RequestParam("category_id") int category_id) {
        Collection<Product> productIdList = productDAO.getProductsForCategory(category_id);
        return new ModelAndView("list", "productList", productIdList);
    }

    /*@RequestMapping("product")
    public ModelAndView productById(@RequestParam("prod_id") int  product_id) {
        Product productById = productDAO.getProductById(product_id);
        return new ModelAndView("product", "productById", productById);
    }*/

    @RequestMapping(value = "paramsForProduct")
    public ModelAndView paramsForProduct(@RequestParam ("prod_id") int product_id) {
        Map<ProductAttribute, ProductParameter> paramsForProduct =
                productDAO.getParamsForProduct(product_id);
        return new ModelAndView("paramsProduct", "paramsForProduct", paramsForProduct);
    }

    @RequestMapping(value = "saveproduct")
    public ModelAndView saveProduct(Product product) {
        productDAO.saveProduct(product);
        return new ModelAndView("saveproduct");
    }

    @RequestMapping(value = "removeproduct")
    public ModelAndView removeProduct(@RequestParam ("prod_id") int product_id) {
        productDAO.removeProduct(product_id);
        return new ModelAndView("removeproduct");
    }

    @RequestMapping("product")
    public ModelAndView productBy(@RequestParam("prod_id") int  product_id) {
        Product productById = productDAO.getProductById(product_id);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("categories", productDAO.getCategories());
        modelMap.addAttribute("product", productById);
        return new ModelAndView("product", "products", modelMap);
    }
}

