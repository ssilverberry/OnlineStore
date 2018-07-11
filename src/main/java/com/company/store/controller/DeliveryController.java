package com.company.store.controller;

import com.company.store.model.entities.Delivery;
import com.company.store.model.impls.DeliveryDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class DeliveryController {

    private DeliveryDAOImpl deliveryDAO;
    @Autowired
    public void setDeliveryDAO(DeliveryDAOImpl deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @RequestMapping(value = "alldeliveries")
    public ModelAndView getAllDeliveries() {
        Collection<Delivery> allDeliveries = deliveryDAO.getAllDeliveries();
        return new ModelAndView("alldeliveries", "allDeliveries", allDeliveries);
    }

    @RequestMapping(value = "deliverybyid")
    public ModelAndView getDeliveryById(@RequestParam(value = "id") int id) {
        Delivery delivery = deliveryDAO.getDeliveryById(id);
        return new ModelAndView("deliveryid", "delivery", delivery);
    }

    @RequestMapping(value = "savedelivery")
    public ModelAndView saveDelivery(@RequestParam(value = "receiverName")  String receiverName,
            @RequestParam(value = "receiverSurname")String receiverSurname,
            @RequestParam(value = "receiverPhone")String receiverPhone,
            @RequestParam(value = "address")String address) {
        Delivery delivery = new Delivery();
        delivery.setReceiverName(receiverName);
        delivery.setReceiverSurname(receiverSurname);
        delivery.setReceiverPhone(receiverPhone);
        delivery.setAddress(address);
        deliveryDAO.saveDelivery(delivery);
        return new ModelAndView("savedelivery");
    }

    @RequestMapping(value = "removedelivery")
    public ModelAndView removeDelivery(@RequestParam(value = "id") int id) {
        deliveryDAO.removeDelivery(id);
        return new ModelAndView("deliveryremove");
    }

    @RequestMapping(value = "updetedelivery")
    public ModelAndView updateStatus(@RequestParam(value = "id") int id,
                                     @RequestParam(value = "status") String status) {
        deliveryDAO.updateStatus(id, status);
        return new ModelAndView("updatestatus");
    }
}
