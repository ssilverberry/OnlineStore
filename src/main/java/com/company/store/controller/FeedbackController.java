package com.company.store.controller;

import com.company.store.model.entities.Feedback;
import com.company.store.model.impls.FeedbackDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class FeedbackController {

    private FeedbackDAOImpl feedbackDAO;

    @RequestMapping(value = "/savefeedback/{feedback}")
    public ModelAndView saveFeedback(Feedback feedback) {
        feedbackDAO.saveFeedback(feedback);
        return new ModelAndView("save_feedback");
    }

    @RequestMapping(value = "/allfeedback()")
    public ModelAndView getAllFeedback() {
        Collection<Feedback> feedback = feedbackDAO.getAllFeedback();
        return new ModelAndView("dysplayallfeedback", "feedback", feedback);
    }

    @RequestMapping(value = "/getAllFeedbackForUser/{user_id}")
    public ModelAndView getAllFeedbackForUser(@PathVariable int user_id) {
        feedbackDAO.getAllFeedbackForUser(user_id);
        return new ModelAndView("AllFeedbackForUser");
    }

    @RequestMapping(value = "/AllFeedbackForProduct/{product_id}")
    public ModelAndView getAllFeedbackForProduct(@PathVariable int product_id) {
        feedbackDAO.getAllFeedbackForProduct(product_id);
        return new ModelAndView("AllFeedbackForProduc");
    }

    @RequestMapping(value = "/UserFeedbackOnProduc/{user_id}/product/{product_id}")
    public ModelAndView getUserFeedbackOnProduc(@PathVariable int user_id, int product_id) {
        feedbackDAO.getUserFeedbackOnProduct(user_id, product_id);
        return new ModelAndView("userFeedbackOnProduct");
    }

    @RequestMapping(value = "/FeedbackById/{feedb_id}")
    public ModelAndView getFeedbackById(@PathVariable int feedb_id) {
        feedbackDAO.getFeedbackById(feedb_id);
        return new ModelAndView("return_feedb_id");

    }

    @RequestMapping(value = "/removeFeedback/{feedb_id}")
    public ModelAndView removeFeedback(@PathVariable int feedb_id) {
        feedbackDAO.removeFeedback(feedb_id);
        return new ModelAndView("remove_feedb_id");

    }
}
