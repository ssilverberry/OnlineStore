package com.company.store.controller;

import com.company.store.model.entities.Payment;
import com.company.store.model.impls.PaymentDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

    private PaymentDAOImpl paymentDAO;


    @RequestMapping("/paymentbyid")
    public ModelAndView paymentById(){
        Payment payment = paymentDAO.getPaymentById(1);
        return new ModelAndView("paymentbyid", "payment", payment);
    }

   /* @RequestMapping("/savepayment")
    public ModelAndView savepayment(){
        Payment payment = paymentDAO.savePayment();
        return new ModelAndView("savepayment", "save", payment);
    }*/

    @RequestMapping("/paymentremove")
    public ModelAndView paymentremove(int payment_id){
        paymentDAO.removePayment(payment_id);
        return new ModelAndView("paymentremove");
    }

}
