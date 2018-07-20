package com.company.store.controller;

import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.services.ProductService;
import com.company.store.model.validators.CreateCategoryFormValidator;

import com.company.store.model.validators.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final ProductService productService;
    private final CreateCategoryFormValidator createCategoryFormValidator;
    private final ProductFormValidator productFormValidator;

    @Autowired
    public AdminController(ProductService productService, CreateCategoryFormValidator createCategoryFormValidator,
                           ProductFormValidator productFormValidator) {
        this.productService = productService;
        this.createCategoryFormValidator = createCategoryFormValidator;
        this.productFormValidator = productFormValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(createCategoryFormValidator);
        binder.setValidator(productFormValidator);
    }

    //WORKS
    @RequestMapping(value = "/mainPage")
    public String getMainPage() {
        return "redirect:/admin/categoriesOperations";
    }

    @RequestMapping(value = "showCreateForm", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("categories", productService.getSubcategories());
        model.addAttribute("product", new Product());
        return "admin/products/createPages/createProduct";
    }

    @RequestMapping(value = "/createProduct/categoryAttrs", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getAttrsList(@RequestParam("categ_id") int id, Model model) {
        Collection<ProductAttribute> attributes = productService.getCategoryAttrs(id);
        List<ProductParameter> parameters = new ArrayList<>();
        for (ProductAttribute attribute : attributes){
            ProductParameter productParameter = new ProductParameter();
            productParameter.setAttrId(attribute.getAttrId());
            parameters.add(productParameter);
        }
        Product product = new Product();
        product.setParams(parameters);
        model.addAttribute("attrsList", attributes);
        model.addAttribute("paramList", parameters);
        model.addAttribute("product", product);
        return new ModelAndView("admin/products/createPages/innerAttrsList");
    }

    @RequestMapping(value = "createProduct", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product, BindingResult result, Model model){
        productFormValidator.validate(product, result);
        if (result.hasErrors()){
            model.addAttribute("categories", productService.getSubcategories());
            model.addAttribute("attrsList", productService.getCategoryAttrs(product.getParentId()));
            return "admin/products/createPages/createProduct";
        } else {
            if (productService.addProduct(product)){
                return "redirect:/admin/products/?prod_id=" + product.getId();
            }
        } return null;
    }

    /*private void populateModel(Model model, Product product){

    }*/

    @RequestMapping(value = "showAttrs", method = RequestMethod.GET)
    public String setProductCategory(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("attributes", productService.getCategoryAttrs(product.getParentId()));
        model.addAttribute("params", new ArrayList<>());
        return "admin/products/productInfo";
    }

    // WORKS
    @RequestMapping(value = "showUpdateForm")
    public String getUpdateProduct(@RequestParam("prod_id") int prod_id, Model model) {
        Product product = productService.getProduct(prod_id);
        product.setParams(productService.getProductParams(prod_id));
        model.addAttribute("product", product);
        model.addAttribute("attrs", productService.getCategoryAttrs(product.getParentId()));
        model.addAttribute("categs", productService.getSubcategories());
        return "admin/products/updatePages/productUpdateForm";
    }

    // WORKS
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Product product, BindingResult result, Model model) {
        productFormValidator.validate(product, result);
        if (result.hasErrors()){
            model.addAttribute("attrs", productService.getCategoryAttrs(product.getParentId()));
            model.addAttribute("categs", productService.getSubcategories());
            return "admin/products/updatePages/productUpdateForm";
        }
        if (productService.updateProduct(product)) {
            return "redirect:/admin/products/?prod_id=" + product.getId();
        } else return null;
    }

    //WORKS
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getUser(@RequestParam("prod_id") int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("prod", product);
        return "admin/products/productInfo";
    }

    //WORKS
    @RequestMapping(value = "/productsOperations", method = RequestMethod.GET)
    public String showProdOperations(Model model) {
        model.addAttribute("categories", productService.getSubcategories());
        model.addAttribute("category", new Product());
        return "admin/products/updatePages/productOperations";
    }

    //WORKS
    @RequestMapping(value = "/updateProduct/productList", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getListByCategory(@RequestParam("categ_id") int id, Model model) {
        model.addAttribute("productList", productService.getCategoryProducts(id));
        return new ModelAndView("admin/products/updatePages/innerProductList");
    }

    //works
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam("prod_id") int id) {
        if (productService.deleteProduct(id)){
            return "redirect:/admin/productsOperations";
        } else return "";
    }

    //WORKS
    @RequestMapping(value = "/categoriesOperations", method = RequestMethod.GET)
    public String showCategsOperations(Model model) {
        model.addAttribute("categories", productService.getCategories());
        return "admin/categories/categoriesOperations";
    }

    //WORKS
    @RequestMapping(value = "/createCategoryForm", method = RequestMethod.GET)
    public String showCreateCategForm(Model model) {
        Product product = new Product();
        List<ProductParameter> list = new ArrayList<>();
        list.add(new ProductParameter());
        product.setParams(list);
        model.addAttribute("category", product);
        return "admin/categories/createCategoryForm";
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute("category") Product category,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categories/createCategoryForm";
        } else if (productService.addCategory(category)) {
            return "redirect:/admin/categoriesOperations";
        }
        else return null;
    }

    @RequestMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam("category_id") int category_id) {
        if (productService.deleteCategory(category_id)) {
            return "redirect:/admin/categoriesOperations";
        }
        else return null;
    }
}
