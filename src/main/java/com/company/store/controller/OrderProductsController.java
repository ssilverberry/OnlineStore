package com.company.store.controller;

import com.company.store.model.entities.Order;
import com.company.store.model.entities.OrderProduct;
import com.company.store.model.entities.Product;
import com.company.store.model.impls.OrderProductsDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class OrderProductsController {

    private OrderProductsDAOImpl orderProductsDAO;

    public void setOrderProductsDAO(OrderProductsDAOImpl orderProductsDAO) {
        this.orderProductsDAO = orderProductsDAO;
    }

    @RequestMapping(value = "OrderProductsById/{id}")
    public ModelAndView getOrderProductsById(@PathVariable("id") String id) {
        Collection<OrderProduct> orderProducts = orderProductsDAO.getOrderProductsById(Integer.parseInt(id));
        return new ModelAndView("orderProductById", "orderProducts", orderProducts);
    }

    @RequestMapping(value = "removeAllProductsFromOrder/{id}")
    public ModelAndView removeAllProductsFromOrder(@PathVariable("id") String id) {
        orderProductsDAO.removeAllProductsFromOrder(Integer.parseInt(id));
        return new ModelAndView("removeAllProducts");
    }

    @RequestMapping(value = "saveProductToOrder/")
    public ModelAndView saveProductToOrder(@PathVariable Product product, Order order, int amount, float price, boolean isUpdate) {
        orderProductsDAO.saveProductToOrder(product, order, amount, price, isUpdate);
        return new ModelAndView("saveProductToOrder");
    }

    @RequestMapping(value = "removeProductFromOrder/{id}/order/{id}")
    public ModelAndView remove(@PathVariable("id") String id) {
        orderProductsDAO.removeProductFromOrder(Integer.parseInt(id), Integer.parseInt(id));
        return new ModelAndView("removeProductFromOrder");
    }
}
