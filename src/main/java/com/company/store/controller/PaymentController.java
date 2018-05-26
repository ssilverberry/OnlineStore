package com.company.store.controller;

import com.company.store.model.entities.Payment;
import com.company.store.model.impls.PaymentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

    private PaymentDAOImpl paymentDAO;

    public void setPaymentDAO(PaymentDAOImpl paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @RequestMapping(value = "paymentbyid")
    public ModelAndView paymentById(@RequestParam(value = "id") int id) {
        Payment payment = paymentDAO.getPaymentById(id);
        return new ModelAndView("paymentbyid", "payment", payment);
    }

    @RequestMapping(value = "savepayment")
    public ModelAndView savepayment(@RequestParam (value = "payment_id") int payment_id,
                                    @RequestParam (value = "amount") int amount,
                                    @RequestParam (value = "type") String type,
                                    @RequestParam (value = "isPaid") Boolean isPaid){
        Payment payment = new Payment();
        payment.setId(payment_id);
        payment.setAmount(amount);
        payment.setType(type);
        payment.setPaid(isPaid);
        Boolean save = paymentDAO.savePayment(payment);
        if(save == true){
        return new ModelAndView("savepayment");
        }else
            return new ModelAndView("nosave");
    }

    @RequestMapping(value = "paymentremove")
    public ModelAndView paymentremove(@RequestParam(value = "id") int id) {
        boolean rem = paymentDAO.removePayment(id);
        if (rem == true) {
            return new ModelAndView("paymentremove");
        } else
            return new ModelAndView("notRemove");
    }

}
