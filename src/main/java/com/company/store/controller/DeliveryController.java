package com.company.store.controller;

import com.company.store.model.entities.Delivery;
import com.company.store.model.impls.DeliveryDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class DeliveryController {

    private DeliveryDAOImpl deliveryDAO;

    @RequestMapping(value = "/alldeliveries")
    public ModelAndView getAllDeliveries() {
        Collection<Delivery> allDeliveries = deliveryDAO.getAllDeliveries();
        return new ModelAndView("alldeliveries", "alldeliver", allDeliveries);
    }

    @RequestMapping(value = "/deliverybyid/{delivery_id}")
    public ModelAndView getDeliveryById(@PathVariable int delivery_id) {
        deliveryDAO.getDeliveryById(delivery_id);
        return new ModelAndView("deliveryid");
    }

    @RequestMapping(value = "/savedelivery/{delivery}")
    public ModelAndView saveDelivery(Delivery delivery) {
        deliveryDAO.saveDelivery(delivery);
        return new ModelAndView("savedelivery");
    }

    @RequestMapping(value = "/removedelivery/{delivery_id}")
    public ModelAndView removeDelivery(@PathVariable int delivery_id) {
        deliveryDAO.removeDelivery(delivery_id);
        return new ModelAndView("deliveryremove");
    }

    @RequestMapping(value = "/updetedelivery/{delivery_id}/status/{status}")
    public ModelAndView updateStatus(@PathVariable int delivery_id, String status) {
        deliveryDAO.updateStatus(delivery_id, status);
        return new ModelAndView("/updatestatus");
    }
}
