package com.company.store.controller;


import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductDAOImpl;
import com.company.store.model.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;



@Controller
public class ProductController {

    private final ProductDAOImpl productDAO;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductDAOImpl productDAO, ProductService productService) {
        this.productDAO = productDAO;
        this.productService = productService;
    }

    // it works
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showCategories() {
        Map<Product, Collection<Product>> categories = productService.getCategories();
        return new ModelAndView("index", "categoryList", categories);
    }

    // it works
    @RequestMapping(value = "subCategsFor", method = RequestMethod.GET)
    public ModelAndView getSubCategories(@RequestParam("categ_id") int categ_id){
        Collection subCategories = productService.getCategoryProducts(categ_id);
        return new ModelAndView("subCategList", "subCategs", subCategories);
    }


    @RequestMapping("product")
    public ModelAndView productBy(@RequestParam("prod_id") int  product_id) {
        return new ModelAndView("product", "product", productService.getProductById(product_id));
    }

    @RequestMapping(value = "categoryProducts")
    public ModelAndView showProductForCategory(@RequestParam("category_id") int category_id) {
        Collection<Product> productIdList = productDAO.getProductsForCategory(category_id);
        ModelMap map = new ModelMap();
        map.addAttribute("products", productService.getCategoryProducts(category_id));
        map.addAttribute("subCategs", productService.getCategoryFilters(category_id));
        return new ModelAndView("list", "productMap", map);
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


}

