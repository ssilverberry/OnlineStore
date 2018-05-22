package com.company.store.controller;

import com.company.store.model.entities.Delivery;
import com.company.store.model.impls.DeliveryDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class DeliveryController {

    private DeliveryDAOImpl deliveryDAO;

    @RequestMapping("/alldeliveries")
    public ModelAndView getAllDeliveries() {
        Collection<Delivery> allDeliveries = deliveryDAO.getAllDeliveries();
        return new ModelAndView("alldeliveries", "alldeliver", allDeliveries);
    }

    @RequestMapping("/deliverybyid")
    public ModelAndView getDeliveryById(int delivery_id) {
        deliveryDAO.getDeliveryById(delivery_id);
        return new ModelAndView("deliveryid");
    }

    @RequestMapping("/savedelivery")
    public ModelAndView saveDelivery(Delivery delivery) {
        deliveryDAO.saveDelivery(delivery);
        return new ModelAndView("savedelivery");
    }

    @RequestMapping("/removedelivery")
    public ModelAndView removeDelivery(int delivery_id) {
        deliveryDAO.removeDelivery(delivery_id);
        return new ModelAndView("deliveryremove");
    }

    @RequestMapping("/updetedelivery")
    public ModelAndView updateStatus(int delivery_id, String status){
        deliveryDAO.updateStatus(delivery_id,status);
        return new ModelAndView("/updatestatus");
    }
}
