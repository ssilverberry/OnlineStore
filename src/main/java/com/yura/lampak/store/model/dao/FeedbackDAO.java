package com.yura.lampak.store.model.dao;


import com.yura.lampak.store.model.entities.Feedback;

import java.util.Collection;

public interface FeedbackDAO {

    Collection<Feedback> getAllFeedback();
    Collection<Feedback> getAllFeedbackForUser(int user_id);
    Collection<Feedback> getAllFeedbackForProduct(int product_id);
    Feedback getUserFeedbackOnProduct(int user_id, int product_id);
    Feedback getFeedbackById(int feedb_id);

    void saveFeedback(Feedback feedback);
    void removeFeedback(int feedb_id);
}
