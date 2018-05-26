package com.company.store.controller;

import com.company.store.model.entities.Delivery;
import com.company.store.model.impls.DeliveryDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class DeliveryController {

    private DeliveryDAOImpl deliveryDAO;

    public void setDeliveryDAO(DeliveryDAOImpl deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @RequestMapping(value = "alldeliveries")
    public ModelAndView getAllDeliveries() {
        Collection<Delivery> allDeliveries = deliveryDAO.getAllDeliveries();
        return new ModelAndView("alldeliveries", "allDeliveries", allDeliveries);
    }

    @RequestMapping(value = "deliverybyid/{id}")
    public ModelAndView getDeliveryById(@PathVariable("id") String id) {
        Delivery delivery = deliveryDAO.getDeliveryById(Integer.parseInt(id));
        return new ModelAndView("deliveryid", "delivery", delivery);
    }

    @RequestMapping(value = "savedelivery/")
    public ModelAndView saveDelivery(Delivery delivery) {
        deliveryDAO.saveDelivery(delivery);
        return new ModelAndView("savedelivery");
    }

    @RequestMapping(value = "removedelivery/{id}")
    public ModelAndView removeDelivery(@PathVariable("id") String id) {
        deliveryDAO.removeDelivery(Integer.parseInt(id));
        return new ModelAndView("deliveryremove");
    }

    @RequestMapping(value = "updetedelivery/{id}")
    public ModelAndView updateStatus(@PathVariable("id") String id, String status) {
        deliveryDAO.updateStatus(Integer.parseInt(id), status);
        return new ModelAndView("updatestatus");
    }
}
