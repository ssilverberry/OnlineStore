package com.company.store.controller;

import com.company.store.entities.Feedback;
import com.company.store.repository.FeedbackDAO;
import com.company.store.repository.UserDAO;
import com.company.store.services.ProductService;
import com.company.store.validators.FeedBackValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class FeedbackController {

    private FeedbackDAO feedbackDAO;
    private UserDAO userDAO;
    private ProductService productService;

    private final FeedBackValidator feedBackValidator;

    @Autowired
    public FeedbackController(FeedBackValidator feedBackValidator) {
        this.feedBackValidator = feedBackValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(feedBackValidator);
    }

    @Autowired
    public void setFeedbackDAO(FeedbackDAO feedbackDAO, ProductService productService, UserDAO userDAO) {
        this.feedbackDAO = feedbackDAO;
        this.productService = productService;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "addFeedback", method = RequestMethod.GET)
    public ModelAndView addFeedBack (@Valid @ModelAttribute("feedback") Feedback feedback,
                               BindingResult result,
                               Model model,
                               @RequestParam("content") String fb,
                               @RequestParam("productId") int  product_id) {
        if (result.hasErrors()) {
            model.addAttribute("product", productService.getProductAndRating(product_id));
            model.addAttribute("feedbackList", feedbackDAO.getAllFeedbackForProduct(product_id));
            return new ModelAndView("product");
        }
        else {
            model.addAttribute("content", feedback.getContent());
            model.addAttribute("product", productService.getProductAndRating(product_id));
            model.addAttribute("feedbackList", feedbackDAO.getAllFeedbackForProduct(product_id));
            feedbackDAO.saveFeedback(new Feedback(userDAO.getById(1000001), product_id,
                4, feedback.getContent()));
            return new ModelAndView("redirect:/product" + "?prod_id=" + product_id);
        }
    }
    @RequestMapping(value = "allfeedback")
    public ModelAndView getAllFeedback() {
        Collection<Feedback> feedback = feedbackDAO.getAllFeedback();
        return new ModelAndView("product", "feedback", feedback);
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
