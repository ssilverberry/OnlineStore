package com.company.store.controller;

import com.company.store.model.entities.Feedback;
import com.company.store.model.impls.FeedbackDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class FeedbackController {

    private FeedbackDAOImpl feedbackDAO;

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

    @RequestMapping(value = "getAllFeedbackForUser/{id}")
    public ModelAndView getAllFeedbackForUser(@PathVariable("id") String id) {
        Collection<Feedback> feedbacks = feedbackDAO.getAllFeedbackForUser(Integer.parseInt(id));
        return new ModelAndView("AllFeedbackForUser", "feedbacks", feedbacks);
    }

    @RequestMapping(value = "AllFeedbackForProduct/{id}")
    public ModelAndView getAllFeedbackForProduct(@PathVariable("id") String id) {
        Collection<Feedback> feedbackForProduct = feedbackDAO.getAllFeedbackForProduct(Integer.parseInt(id));
        return new ModelAndView("AllFeedbackForProduc", "feedbackForProduct", feedbackForProduct);
    }

    @RequestMapping(value = "UserFeedbackOnProduc/{id}/product/{id}")
    public ModelAndView getUserFeedbackOnProduc(@PathVariable("id") String id) {
        Feedback userFeedbackOnProduct = feedbackDAO.getUserFeedbackOnProduct(Integer.parseInt(id), Integer.parseInt(id));
        return new ModelAndView("userFeedbackOnProduct", "userFeedbackOnProduct", userFeedbackOnProduct);
    }

    @RequestMapping(value = "FeedbackById/{id}")
    public ModelAndView getFeedbackById(@PathVariable("id") String id) {
        Feedback feedback = feedbackDAO.getFeedbackById(Integer.parseInt(id));
        return new ModelAndView("return_feedb_id", "feedback", feedback);

    }

    @RequestMapping(value = "removeFeedback/{id}")
    public ModelAndView removeFeedback(@PathVariable("id") String id) {
        feedbackDAO.removeFeedback(Integer.parseInt(id));
        return new ModelAndView("remove_feedb_id");

    }
}
