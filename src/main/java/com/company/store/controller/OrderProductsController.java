package com.company.store.controller;

import com.company.store.model.entities.Order;
import com.company.store.model.entities.OrderProduct;
import com.company.store.model.entities.Product;
import com.company.store.model.impls.OrderProductsDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class OrderProductsController {

    private OrderProductsDAOImpl orderProductsDAO;

    @RequestMapping(value = "/OrderProductsById/{order_id}")
    public ModelAndView getOrderProductsById(@PathVariable int order_id) {
        Collection<OrderProduct> orderProducts = orderProductsDAO.getOrderProductsById(order_id);
        return new ModelAndView("orderProductById", "orderProducts", orderProducts);
    }

    @RequestMapping(value = "/removeAllProductsFromOrder/{order_id}")
    public ModelAndView removeAllProductsFromOrder(@PathVariable int order_id) {
        orderProductsDAO.removeAllProductsFromOrder(order_id);
        return new ModelAndView("removeAllProducts");
    }

    @RequestMapping(value = "/saveProductToOrder/{product}{order}{amount}{price}{isUpdate}")
    public ModelAndView saveProductToOrder(@PathVariable Product product, Order order, int amount, float price, boolean isUpdate) {
        orderProductsDAO.saveProductToOrder(product, order, amount, price, isUpdate);
        return new ModelAndView("/saveProductToOrder");
    }

    @RequestMapping(value = "/removeProductFromOrder{product_id}{order_id}")
    public ModelAndView remove(@PathVariable int product_id, int order_id) {
        orderProductsDAO.removeProductFromOrder(product_id, order_id);
        return new ModelAndView("/removeProductFromOrder");
    }
}
