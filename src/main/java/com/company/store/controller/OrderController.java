package com.company.store.controller;

import com.company.store.model.entities.Order;
import com.company.store.model.impls.OrderDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
public class OrderController {

    private OrderDAOImpl orderDAO;
    @Autowired
    public void setOrderDAO(OrderDAOImpl orderDAO) {
        this.orderDAO = orderDAO;
    }

    @RequestMapping(value = "OrderById/{id}")
    public ModelAndView getOrderById(@PathVariable("id") String id) {
        Order order = orderDAO.getOrderById(Integer.parseInt(id));
        return new ModelAndView("orderById", "order", order);
    }

    @RequestMapping(value = "AllOrdersForUser/{id}")
    public ModelAndView getAllOrdersForUser(@PathVariable("id") String id) {
        Collection<Order> orders = orderDAO.getAllOrdersForUser(Integer.parseInt(id));
        return new ModelAndView("AllOrdersForUser", "AllOrdersUser", orders);
    }

    @RequestMapping(value = "AllOrders")
    public ModelAndView getAllOrders() {
        Collection<Order> orders = orderDAO.getAllOrders();
        return new ModelAndView("allorders", "Allorders", orders);
    }

    @RequestMapping("order")
    public String order(Map<String, Object> model) {
        model.put("saveOrderForm", new Order());
        return "order";
    }

    @RequestMapping(value = "saveOrder")
    public String saveOrder(@ModelAttribute ("saveOrderForm") Order order,
                            BindingResult result,
                            Map<String, Object> model) {
        return "success";
    }

    @RequestMapping(value = "removeOrderById")
    public String removeOrderById(@RequestParam(value = "id")int id) {
        orderDAO.removeOrderById(id);
        return "removeorder_id";
    }

    @RequestMapping ("success")
    public String success () {
        return "success";
    }
}
