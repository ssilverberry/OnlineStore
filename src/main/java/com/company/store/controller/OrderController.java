package com.company.store.controller;

import com.company.store.model.entities.Order;
import com.company.store.model.impls.OrderDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class OrderController {
    private OrderDAOImpl orderDAO;

    @RequestMapping(value = "/OrderById/{order_id}")
    public ModelAndView getOrderById(@PathVariable int order_id){
       Order order = orderDAO.getOrderById(order_id);
        return new ModelAndView("orderById", "order",order);
    }

    @RequestMapping(value = "/AllOrdersForUser/{order_id}")
    public ModelAndView getAllOrdersForUser(@PathVariable int user_id){
        Collection<Order> orders = orderDAO.getAllOrdersForUser(user_id);
        return new ModelAndView("AllOrdersForUser", "AllOrdersUser", orders);
    }

    @RequestMapping(value = "/AllOrders")
    public ModelAndView getAllOrders(){
        Collection<Order> orders = orderDAO.getAllOrders();
        return new ModelAndView("allorders","Allorders",orders);
    }
    @RequestMapping(value = "/saveOrder/{order}")
    public ModelAndView saveOrder(Order order){
        orderDAO.saveOrder(order);
        return new ModelAndView("saveorder");
    }

    @RequestMapping(value = "/removeOrderById/{order_id}")
    public ModelAndView removeOrderById(int order_id){
        orderDAO.removeOrderById(order_id);
        return new ModelAndView("removeorder_id");
    }
}
