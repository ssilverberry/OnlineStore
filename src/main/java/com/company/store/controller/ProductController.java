package com.company.store.controller;


import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;
import com.company.store.model.impls.ProductDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


@Controller
public class ProductController {
    // @Qualifier("productDAOImpl")
     @Autowired
    private ProductDAOImpl productDAO;

    public void setProductDAO(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView showAllProduct() {
        Collection<Product> categoryList = productDAO.getCategories();
        return new ModelAndView("index", "categoryList", categoryList);

    }

    @RequestMapping(value = "productCategoriesId/{id}")
    public ModelAndView showProductForCategory(@PathVariable ("id") String category_id) {
        Collection<Product> productIdList =
                productDAO.getProductsForCategory(Integer.parseInt(category_id));
        return new ModelAndView("product", "productCategoriesId", productIdList);
    }

    @RequestMapping(value = "product/{id}")
    public ModelAndView productById(@PathVariable(value = "id") String product_id) {
        Product productById = productDAO.getProductById(Integer.parseInt(product_id));
        return new ModelAndView("product", "productById", productById);
    }

    @RequestMapping(value = "paramsForProduct/{id}")
    public ModelAndView paramsForProduct(@PathVariable("id") String product_id) {
        Map<ProductAttribute, ProductParameter> paramsForProduct =
                productDAO.getParamsForProduct(Integer.parseInt(product_id));
        return new ModelAndView("paramsProduct", "paramsForProduct", paramsForProduct);
    }

   /* @RequestMapping(value = "/saveproduct")
    public ModelAndView saveProduct(Product product) {
        productDAO.saveProduct(product);
        return new ModelAndView("saveproduct");
    }*/

    @RequestMapping(value = "removeproduct/{id}")
    public ModelAndView removeProduct(@PathVariable("id") String product_id) {
        productDAO.removeProduct(Integer.parseInt(product_id));
        return new ModelAndView("productRemoved");
    }
}

