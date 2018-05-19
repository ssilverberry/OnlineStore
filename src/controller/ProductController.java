package com.company.store.controller;


import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/productCategoriesId")
    public ModelAndView showProductForCategory() {
        Collection<Product> productIdList = productDAO.getProductsForCategory(2);
        return new ModelAndView("product", "productCategoriesId", productIdList);
    }

    @RequestMapping("/productById")
    public ModelAndView productById() {
        Product productById = productDAO.getProductById(3);
        return new ModelAndView("productId", "productById", productById);
    }
    @RequestMapping("/paramsForProduct")
    public ModelAndView paramsForProduct(){
        Map<ProductAttribute, ProductParameter> paramsForProduct = productDAO.getParamsForProduct(1);
        return new ModelAndView("paramsProduct", "paramsForProduct",paramsForProduct);
    }
    /*test commi from my account*/
}

