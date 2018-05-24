package com.company.store.controller;

import com.company.store.model.entities.Payment;
import com.company.store.model.impls.PaymentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

    private PaymentDAOImpl paymentDAO;

    public void setPaymentDAO(PaymentDAOImpl paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @RequestMapping(value = "paymentbyid/{id}")
    public ModelAndView paymentById(@PathVariable("id") String id) {
        Payment payment = paymentDAO.getPaymentById(Integer.parseInt(id));
        return new ModelAndView("paymentbyid", "payment", payment);
    }

    @RequestMapping(value = "savepayment")
    public ModelAndView savepayment(Payment payment) {
        paymentDAO.savePayment(payment);
        return new ModelAndView("savepayment");
    }

    @RequestMapping(value = "paymentremove/{id}")
    public ModelAndView paymentremove(@PathVariable("id") String id) {
        paymentDAO.removePayment(Integer.parseInt(id));
        return new ModelAndView("paymentremove");
    }

}
