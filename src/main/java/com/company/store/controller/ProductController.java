package com.company.store.controller;


import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.Collection;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {
    /* @Qualifier("productDAOImpl")
     @Autowired*/
    private ProductDAOImpl productDAO;

    @RequestMapping("/categories")
    public ModelAndView showAllProduct() {
        Collection<Product> categoryList = productDAO.getCategories();
        return new ModelAndView("categories", "categoryList", categoryList);
    }

    @RequestMapping(value = "/productCategoriesId/{id}")
    public ModelAndView showProductForCategory(@PathVariable int category_id) {
        Collection<Product> productIdList = productDAO.getProductsForCategory(category_id);
        return new ModelAndView("product", "productCategoriesId", productIdList);
    }

    @RequestMapping(value = "/productById/{id}")
    public ModelAndView productById(@PathVariable int product_id) {
        Product productById = productDAO.getProductById(product_id);
        return new ModelAndView("productId", "productById", productById);
    }

    @RequestMapping(value = "/paramsForProduct/{id}")
    public ModelAndView paramsForProduct(@PathVariable int product_id) {
        Map<ProductAttribute, ProductParameter> paramsForProduct = productDAO.getParamsForProduct(product_id);
        return new ModelAndView("paramsProduct", "paramsForProduct", paramsForProduct);
    }

    @RequestMapping(value = "/saveproduct/{product}")
    public ModelAndView saveProduct(Product product) {
        productDAO.saveProduct(product);
        return new ModelAndView("saveproduct");
    }

    @RequestMapping(value = "/removeproduct/{id}")
    public ModelAndView removeProduct(@PathVariable int product_id) {
        productDAO.removeProduct(product_id);
        return new ModelAndView("removeproduct");
    }
}

