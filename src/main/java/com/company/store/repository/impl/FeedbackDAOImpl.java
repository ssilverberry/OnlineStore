package com.company.store.repository.impl;

import com.company.store.repository.FeedbackDAO;
import com.company.store.entities.Feedback;

import com.company.store.entities.User;
import com.company.store.entities.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class FeedbackDAOImpl implements FeedbackDAO {

    private static final Logger log = LogManager.getLogger(FeedbackDAOImpl.class);

    private static final String GET_ALL_FEEDBACK = "SELECT * FROM FEEDBACK";
    private static final String GET_FEEDBACK_BY_ID = "SELECT * FROM FEEDBACK WHERE FEEDBACK_ID = ?";
    private static final String GET_ALL_USER_FEEDBACK = "SELECT FEEDBACK.*, USERS.USER_NAME FROM FEEDBACK " +
            "JOIN USERS ON USERS.USER_ID = FEEDBACK.USER_ID WHERE FEEDBACK.USER_ID=?";

    private static final String GET_ALL_FEEDBACK_FOR_PRODUCT = "SELECT * FROM FEEDBACK WHERE product_id=?";

    private static final String GET_PRODUCT_RATING = "SELECT round(avg(feedback_raiting), 2) FROM feedback " +
            "WHERE product_id=?";

    private static final String GET_USER_FEEDBACK_ON_PRODUCT = "SELECT * FROM FEEDBACK WHERE USER_ID = ? " +
                                                                                            "AND PRODUCT_ID = ?";

    /**
     * Parameters in this order: USER_ID, PRODUCT_ID, FEEDBACK_RATING, FEEDBACK_MESSAGE
     */
    private static final String INSERT_FEEDBACK = "INSERT INTO FEEDBACK VALUES (DEFAULT, ?, ?, ?, ?)";

    private static final String UPDATE_FEEDBACK = "UPDATE feedback SET user_id=?, product_id=?, feedback_rating=?, " +
                                                                      "feedback_message=? WHERE feedback_id=?";

    private static final String DELETE_FEEDBACK = "DELETE FROM FEEDBACK WHERE FEEDBACK_ID = ?";

    /**
     * Instance of global datasource to get connection from pool.
     */
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * save feedback to database
     */
    @Override
    public boolean saveFeedback(Feedback feedback) {
        int feedb_id = feedback.getId();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(feedb_id != 0 ? UPDATE_FEEDBACK : INSERT_FEEDBACK)){
            ps.setInt(1, feedback.getUser().getId());
            ps.setInt(2, feedback.getProductId());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getContent());
            if (feedb_id != 0){
                ps.setInt(5, feedb_id);
            }
            ps.executeUpdate();
            log.debug("Feedback was saved to database! Info: " + feedback.toString());
        } catch (SQLException e) {
            log.error("Failed to save feedback into database! Info: " + feedback.toString(), e);
            return false;
        }
        return true;
    }

    @Override
    public float getProductRating(int product_id) {
        float rating = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_RATING)){
            ps.setInt(1, product_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                rating = resultSet.getFloat(1);
                System.out.println("RATING = " + rating);
                log.debug("Product rating was received!");
            }
        } catch (SQLException e) {
            log.error("Failed to receive product rating! ", e);
        }
        return rating;
    }

    /**
     * Return all feedback, which store in database
     */
    @Override
    public Collection<Feedback> getAllFeedback() {
        Collection<Feedback> feedback = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_FEEDBACK)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                feedback.add(parseFeedback(resultSet));
            } log.debug("All feedback was received!");
        } catch (SQLException e) {
            log.error("Failed to receive all feedback! ", e);
        }
        return feedback;
    }

    /**
     * Return collection of feedback for specific user_id
     */
    @Override
    public Collection<Feedback> getAllFeedbackForUser(int user_id) {
        Collection<Feedback> feedback = getFeedbackFor(user_id, GET_ALL_USER_FEEDBACK);
        log.debug("Feedback list was received from database for user_id: " + user_id);
        return feedback;
    }

    /**
     * Return collection of feedback for specific product_id
     */
    @Override
    public Collection<Feedback> getAllFeedbackForProduct(int product_id) {
        Collection<Feedback> feedback = getFeedbackFor(product_id, GET_ALL_FEEDBACK_FOR_PRODUCT);
        log.debug("Feedback list was received from database for product_id: " + product_id);
        return feedback;
    }

    /**
     * Return collection of feedback for specific id and query params.
     */
    private Collection<Feedback> getFeedbackFor(int id, String query){
        Collection<Feedback> feedback = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                feedback.add(parseFeedback(resultSet));
            }
        } catch (SQLException e) {
            log.error("Failed to receive feedback by id: " + id + ", Query: " + query, e);
        }
        return feedback;
    }

    /**
     * Parse cortege with feedback credentials to object Feedback.
     */
    private Feedback parseFeedback(ResultSet resultSet) {
        Feedback feedback = new Feedback();
        User user = User.newBuilder().build();
        try {
            feedback.setId(resultSet.getInt(1));
            user.setId(resultSet.getInt(2));
            feedback.setProductId(resultSet.getInt(3));
            feedback.setRating(resultSet.getInt(4));
            feedback.setContent(resultSet.getString(5));
            user.setName(resultSet.getString(6));
            feedback.setUser(user);
        } catch (SQLException e) {
            log.error("Parsing of feedback was failed! ", e);
        } return feedback;
    }

    /**
     * Return user feedback to the product by specific user_id and product_id.
     */
    @Override
    public Feedback getUserFeedbackOnProduct(int user_id, int product_id) {
        Feedback feedback = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_USER_FEEDBACK_ON_PRODUCT)){
            ps.setInt(1, user_id);
            ps.setInt(2, product_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                feedback = parseFeedback(resultSet);
            }
        } catch (SQLException e) {
            log.error("Failed to receive feedback by user_id: " + user_id + " on product_id: " + product_id, e);
        }
        return feedback;
    }

    /**
     *
     * Return feedback by specific id.
     */
    @Override
    public Feedback getFeedbackById(int feedb_id) {
        Feedback feedback = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_FEEDBACK_BY_ID)) {
            ps.setInt(1, feedb_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                feedback = parseFeedback(resultSet);
                log.debug("Feedback was received  by id: " + feedb_id);
            }
        } catch (SQLException e) {
            log.error("Failed to receive feedback by id: " + feedb_id, e);
        } return feedback;
    }

    /**
     * Remove feedback from database by specific id.
     */
    @Override
    public boolean removeFeedback(int feedb_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_FEEDBACK)) {
            ps.setInt(1, feedb_id);
            ps.executeUpdate();
            log.debug("feedback was deleted by id: " + feedb_id);
        } catch (SQLException e) {
            log.error("Failed to delete feedback by id: " + feedb_id, e);
            return false;
        }
        return true;
    }
}
