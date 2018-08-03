package com.company.store.controller;

import com.company.store.entities.Feedback;
import com.company.store.entities.Product;
import com.company.store.entities.ProductAttribute;
import com.company.store.entities.ProductParameter;
import com.company.store.repository.FeedbackDAO;
import com.company.store.repository.ProductDAO;
import com.company.store.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
public class ProductController {

    private final ProductDAO productDAO;
    private final ProductService productService;
    private final FeedbackDAO feedbackDAO;

    @Autowired
    public ProductController(ProductDAO productDAO, ProductService productServiceImpl, FeedbackDAO feedback) {
        this.productDAO = productDAO;
        this.productService = productServiceImpl;
        this.feedbackDAO = feedback;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showCategories() {
        Map<Product, Collection<Product>> categories = productService.getCategories();
        return new ModelAndView("index", "categoryList", categories);
    }

    @RequestMapping(value = "subCategsFor", method = RequestMethod.GET)
    public ModelAndView getSubCategories(@RequestParam("categ_id") int categ_id){
        Collection subCategories = productService.getCategoryProducts(categ_id);
        return new ModelAndView("subCategList", "subCategs", subCategories);
    }

    @RequestMapping("product")
    public ModelAndView productBy(@RequestParam("prod_id") int  product_id, Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("feedbackList", feedbackDAO.getAllFeedbackForProduct(product_id));
        model.addAttribute("product", productService.getProductAndRating(product_id));
        return new ModelAndView("product");
    }

    @RequestMapping(value = "categoryProducts")
    public ModelAndView showProductForCategory(@RequestParam("category_id") int category_id) {
        ModelMap map = new ModelMap();
        map.addAttribute("products", productService.getCategoryProducts(category_id));
        map.addAttribute("subCategs", productService.getCategoryAttrs(category_id));
        return new ModelAndView("list", "productMap", map);
    }

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
