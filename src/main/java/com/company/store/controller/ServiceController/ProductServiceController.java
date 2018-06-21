package com.company.store.controller.ServiceController;

import com.company.store.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductServiceController {
    @Autowired
    private ProductService productService;
}
