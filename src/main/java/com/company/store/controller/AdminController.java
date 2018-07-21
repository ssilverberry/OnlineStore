package com.company.store.controller;

import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.formObjects.CategoryFormObject;
import com.company.store.model.services.ProductService;
import com.company.store.model.validators.CategoryFormValidator;

import com.company.store.model.validators.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryFormValidator categoryFormValidator;
    private final ProductFormValidator productFormValidator;

    @Autowired
    public AdminController(ProductService productService, CategoryFormValidator categoryFormValidator,
                           ProductFormValidator productFormValidator) {
        this.productService = productService;
        this.categoryFormValidator = categoryFormValidator;
        this.productFormValidator = productFormValidator;
    }

    @InitBinder(value = "category")
    protected void initCategoryBinder(WebDataBinder binder) {
        binder.setValidator(categoryFormValidator);
    }

    @InitBinder(value = "product")
    protected void initProductBinder(WebDataBinder binder) {
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
        List<ProductAttribute> attributes = productService.getCategoryAttrs(id);
        attributes.sort(Comparator.comparing(o -> String.valueOf(o.getAttrId())));
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
        Map<ProductAttribute, ProductParameter> attrParamMap = productService.getParamsForProduct(prod_id);
        List<ProductParameter> parameters = new ArrayList<>(attrParamMap.values());
        product.setParams(parameters);
        List<ProductAttribute> attributes = new ArrayList<>(attrParamMap.keySet());
        //attributes.sort(Comparator.comparing(o -> String.valueOf(o.getAttrId())));
        model.addAttribute("product", product);
        model.addAttribute("attrs", attributes);
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
        /*model.addAttribute("categ", new Product());*/
        return "admin/products/updatePages/productOperations";
    }

    //WORKS
    @RequestMapping(value = "/updateProduct/productList", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getListByCategory(@RequestParam("categ_id") int id, Model model) {
        model.addAttribute("productList", productService.getCategoryProducts(id));
        return new ModelAndView("admin/products/updatePages/innerProductList");
    }

    //works
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("prod_id") int id) {
        if (productService.deleteProduct(id)){
            return "redirect:/admin/productsOperations";
        } else return null;
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
        CategoryFormObject object = new CategoryFormObject();
        List<ProductAttribute> attributes = new ArrayList<>();
        attributes.add(new ProductAttribute());
        object.setAttributes(attributes);
        model.addAttribute("category", object);
        return "admin/categories/createCategoryForm";
    }

    //WORKS
    @RequestMapping(value = "/updateSubcategoryForm", method = RequestMethod.GET)
    public String showUpdateSubcategForm(@RequestParam("subcategory_id") int subcategory_id, Model model) {
        Product subcategory = productService.getProduct(subcategory_id);
        Product category = productService.getProduct(subcategory.getParentId());
        CategoryFormObject object = new CategoryFormObject(category.getId(), category.getName(), subcategory.getName());
        object.setSubcategoryId(subcategory_id);
        object.setAttributes(productService.getCategoryAttrs(subcategory_id));
        object.getAttributes().sort(Comparator.comparing(o -> String.valueOf(o.getAttrId())));
        model.addAttribute("categories", productService.getCategories().keySet());
        model.addAttribute("category", object);
        return "admin/categories/updateSubcategoryForm";
    }

    @RequestMapping(value = "/updateSubcategory", method = RequestMethod.POST)
    public String updateSubcategory(@Valid @ModelAttribute("category") CategoryFormObject category,
                                 BindingResult result) {

        if (result.hasErrors()) {
            return "admin/categories/updateSubcategoryForm";
        } else if (productService.updateSubcategory(category)) {
            return "redirect:/admin/categoriesOperations";
        }
        else return "redirect:/admin/categoriesOperations";
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(@ Valid @ModelAttribute("category") CategoryFormObject category,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categories/createCategoryForm";
        } else if (productService.addCategory(category)) {
            return "redirect:/admin/categoriesOperations";
        }
        else return "redirect:/admin/categoriesOperations";
    }

    @RequestMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam("category_id") int category_id) {
        if (productService.deleteCategory(category_id)) {
            return "redirect:/admin/categoriesOperations";
        }
        else return null;
    }
}
