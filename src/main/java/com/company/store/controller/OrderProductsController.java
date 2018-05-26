package com.company.store.controller;

import com.company.store.model.entities.Order;
import com.company.store.model.entities.OrderProduct;
import com.company.store.model.entities.Product;
import com.company.store.model.impls.OrderProductsDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Collection;

@Controller
public class OrderProductsController {

    private OrderProductsDAOImpl orderProductsDAO;

    public void setOrderProductsDAO(OrderProductsDAOImpl orderProductsDAO) {
        this.orderProductsDAO = orderProductsDAO;
    }

    @RequestMapping(value = "OrderProductsById")
    public ModelAndView getOrderProductsById(@RequestParam(value = "id") int id) {
        Collection<OrderProduct> orderProducts = orderProductsDAO.getOrderProductsById(id);
        return new ModelAndView("orderProductById", "orderProducts", orderProducts);
    }

    @RequestMapping(value = "removeAllProductsFromOrder")
    public ModelAndView removeAllProductsFromOrder(@RequestParam("id") int id) {
        orderProductsDAO.removeAllProductsFromOrder(id);
        return new ModelAndView("removeAllProducts");
    }

    @RequestMapping(value = "saveProductToOrder")
    public ModelAndView saveProductToOrder(@RequestParam(value = "id") int product,
                                           @RequestParam(value = "order_id") int order_id,
                                           @RequestParam(value = "user_id") int user_id,
                                           @RequestParam(value = "amount") int amount,
                                           @RequestParam(value = "price") float price,
                                           @RequestParam(value = "isUpdate") boolean isUpdate) {
        Order order = new Order();
        order.setId(order_id);
        order.setUserId(user_id);
        Boolean rec = orderProductsDAO.saveProductToOrder(product, order, amount, price, isUpdate);
        if(rec){
        return new ModelAndView("saveProductToOrder");
        } else
            return new ModelAndView("noSave");

    }

    @RequestMapping(value = "removeProductFromOrderorder")
    public ModelAndView remove(@RequestParam(value = "id") int id, @RequestParam(value = "order_id") int order_id) {
        orderProductsDAO.removeProductFromOrder(id, order_id);
        return new ModelAndView("removeProductFromOrder");
    }
}
