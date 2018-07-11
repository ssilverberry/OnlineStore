package com.company.store.controller;

import com.company.store.model.entities.Feedback;
import com.company.store.model.impls.FeedbackDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class FeedbackController {

    private FeedbackDAOImpl feedbackDAO;

    @Autowired
    public void setFeedbackDAO(FeedbackDAOImpl feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    @RequestMapping(value = "savefeedback")
    public ModelAndView saveFeedback(Feedback feedback) {
        feedbackDAO.saveFeedback(feedback);
        return new ModelAndView("save_feedback");
    }

    @RequestMapping(value = "allfeedback")
    public ModelAndView getAllFeedback() {
        Collection<Feedback> feedback = feedbackDAO.getAllFeedback();
        return new ModelAndView("dysplayallfeedback", "feedback", feedback);
    }

    @RequestMapping(value = "getAllFeedbackForUser")
    public ModelAndView getAllFeedbackForUser(@RequestParam(value = "id")int id) {
        Collection<Feedback> feedbacks = feedbackDAO.getAllFeedbackForUser(id);
        return new ModelAndView("AllFeedbackForUser", "feedbacks", feedbacks);
    }

    @RequestMapping(value = "AllFeedbackForProduct")
    @ResponseBody
    public Collection<Feedback> getAllFeedbackForProduct(@RequestParam(value = "id") int id) {
        return feedbackDAO.getAllFeedbackForProduct(id);
    }

    @RequestMapping(value = "UserFeedbackOnProduc/product/{id}")
    public ModelAndView getUserFeedbackOnProduc(@RequestParam(value = "id")int id,
                                                @RequestParam(value = "product_id") int product_id) {
        Feedback userFeedbackOnProduct = feedbackDAO.getUserFeedbackOnProduct(id, product_id);
        return new ModelAndView("userFeedbackOnProduct", "userFeedbackOnProduct", userFeedbackOnProduct);
    }

    @RequestMapping(value = "FeedbackById")
    public ModelAndView getFeedbackById(@RequestParam(value = "id") int id) {
        Feedback feedback = feedbackDAO.getFeedbackById(id);
        return new ModelAndView("return_feedb_id", "feedback", feedback);

    }

    @RequestMapping(value = "removeFeedback")
    public ModelAndView removeFeedback(@RequestParam(value = "id") int id) {
        feedbackDAO.removeFeedback(id);
        return new ModelAndView("remove_feedb_id");

    }
}
