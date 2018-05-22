package com.company.store.controller;

import com.company.store.model.entities.Order;
import com.company.store.model.impls.OrderDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class OrderController {
    private OrderDAOImpl orderDAO;

    @RequestMapping("/OrderById")
    public ModelAndView getOrderById(int order_id){
       Order order = orderDAO.getOrderById(order_id);
        return new ModelAndView("orderById", "order",order);
    }

    @RequestMapping("/AllOrdersForUser")
    public ModelAndView getAllOrdersForUser(int user_id){
        Collection<Order> orders = orderDAO.getAllOrdersForUser(user_id);
        return new ModelAndView("AllOrdersForUser", "AllOrdersUser", orders);
    }

    @RequestMapping("/AllOrders")
    public ModelAndView getAllOrders(){
        Collection<Order> orders = orderDAO.getAllOrders();
        return new ModelAndView("allorders","Allorders",orders);
    }
    @RequestMapping("/saveOrder")
    public ModelAndView saveOrder(Order order){
        orderDAO.saveOrder(order);
        return new ModelAndView("saveorder");
    }

    @RequestMapping("/removeOrderById")
    public ModelAndView removeOrderById(int order_id){
        orderDAO.removeOrderById(order_id);
        return new ModelAndView("removeorder_id");
    }
}
