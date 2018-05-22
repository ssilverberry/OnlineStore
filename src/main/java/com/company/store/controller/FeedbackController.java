package com.company.store.controller;

import com.company.store.model.entities.Feedback;
import com.company.store.model.impls.FeedbackDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class FeedbackController {

    private FeedbackDAOImpl feedbackDAO;

    @RequestMapping("/savefeedback")
    public ModelAndView saveFeedback(Feedback feedback) {
        feedbackDAO.saveFeedback(feedback);
        return new ModelAndView("save_feedback");
    }

    @RequestMapping("/allfeedback()")
    public ModelAndView getAllFeedback() {
        Collection<Feedback> feedback = feedbackDAO.getAllFeedback();
        return new ModelAndView("dysplayallfeedback", "feedback", feedback);
    }

    @RequestMapping("/getAllFeedbackForUser")
    public ModelAndView getAllFeedbackForUser(int user_id) {
        feedbackDAO.getAllFeedbackForUser(user_id);
        return new ModelAndView("AllFeedbackForUser");
    }

    @RequestMapping("/AllFeedbackForProduct")

    public ModelAndView getAllFeedbackForProduct(int product_id) {
        feedbackDAO.getAllFeedbackForProduct(product_id);
        return new ModelAndView("AllFeedbackForProduc");
    }

    @RequestMapping("/UserFeedbackOnProduc")

    public ModelAndView getUserFeedbackOnProduc(int user_id, int product_id) {
        feedbackDAO.getUserFeedbackOnProduct(user_id, product_id);
        return new ModelAndView("userFeedbackOnProduct");
    }

    @RequestMapping("/FeedbackById")
    public ModelAndView getFeedbackById(int feedb_id) {
        feedbackDAO.getFeedbackById(feedb_id);
        return new ModelAndView("return_feedb_id");

    }

    @RequestMapping("/removeFeedback")
    public ModelAndView removeFeedback(int feedb_id) {
        feedbackDAO.removeFeedback(feedb_id);
        return new ModelAndView("remove_feedb_id");

    }
}
