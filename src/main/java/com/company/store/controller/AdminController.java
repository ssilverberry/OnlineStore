package com.company.store.controller;

import com.company.store.model.entities.Product;
import com.company.store.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/mainPage")
    public String getMainPage() {
        return "admin";
    }

    @RequestMapping(value = "showCreateForm", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("categories", productService.getSubcategories());
        model.addAttribute("product", new Product());
        return "admin/createProduct";
    }

    @RequestMapping(value = "showAttrs", method = RequestMethod.GET)
    public String setProductCategory(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("attributes", productService.getCategoryFilters(product.getParentId()));
        model.addAttribute("params", new ArrayList<>());
        return "admin/product";
    }

    // WORKS
    @RequestMapping(value = "showUpdateForm")
    public String getUpdateProduct(@RequestParam("prod_id") int prod_id, Model model) {
        Product product = productService.getProduct(prod_id);
        product.setParams(productService.getProductParams(prod_id));
        model.addAttribute("product", product);
        model.addAttribute("attrs", productService.getCategoryFilters(product.getParentId()));
        model.addAttribute("categs", productService.getSubcategories());
        return "admin/products/productUpdateForm";
    }

    // WORKS
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Product product) {
        product.setCategory(false);
        if (productService.updateProduct(product)) {
            productService.saveProductParams(product.getParams());
            return "redirect:/product?prod_id=" + product.getId();
        } else return null;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("prod", product);
        return "admin/product";
    }

    //test
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getUser(@RequestParam("prod_id") int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("prod", product);
        return "admin/product";
    }

    @RequestMapping(value = "/changeProduct", method = RequestMethod.GET)
    public String showUser(Model model) {
        model.addAttribute("categories", productService.getSubcategories());
        model.addAttribute("category", new Product());
        return "admin/products/productOperations";
    }

    @RequestMapping(value = "/changeProduct", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getListByCategory(@RequestParam("categ_id") int id, Model model) {
        model.addAttribute("productList", productService.getCategoryProducts(id));
        return new ModelAndView("admin/products/innerProductList");
    }

    /*@RequestMapping(value = "getProductPage")
    public String getProductPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("prod", productService.getProduct(id));
        return "admin/product";
    }*/

}
