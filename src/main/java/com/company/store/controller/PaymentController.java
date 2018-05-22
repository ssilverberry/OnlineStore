package com.company.store.controller;

import com.company.store.model.entities.Payment;
import com.company.store.model.impls.PaymentDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

    private PaymentDAOImpl paymentDAO;


    @RequestMapping(value = "/paymentbyid/{payment_id}")
    public ModelAndView paymentById(@PathVariable int payment_id) {
        Payment payment = paymentDAO.getPaymentById(payment_id);
        return new ModelAndView("paymentbyid", "payment", payment);
    }

    @RequestMapping(value = "/savepayment/{payment}")
    public ModelAndView savepayment(Payment payment) {
        paymentDAO.savePayment(payment);
        return new ModelAndView("savepayment");
    }

    @RequestMapping(value = "/paymentremove/{payment_id}")
    public ModelAndView paymentremove(@PathVariable int payment_id) {
        paymentDAO.removePayment(payment_id);
        return new ModelAndView("paymentremove");
    }

}
