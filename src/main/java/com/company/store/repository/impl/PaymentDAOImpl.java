package com.company.store.repository.impl;


import com.company.store.repository.PaymentDAO;
import com.company.store.entities.Payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    private static final Logger log = LogManager.getLogger(PaymentDAOImpl.class);

    private static final String GET_PAYMENT_BY_ID = "SELECT * FROM PAYMENTS WHERE PAYMENT_ID = ?";

    /**
     * Parameters in this order: PAYMENT_TYPE, PAYMENT_AMOUNT, ISPAID
     */
    private static final String INSERT_PAYMENT = "INSERT INTO PAYMENTS VALUES (DEFAULT, ?, ?, ?)";
    private static final String UPDATE_PAYMENT = "UPDATE payments SET payment_type=?, payment_amount=?, ispaid=? " +
                                                                 "WHERE payment_id=?";
    private static final String DELETE_PAYMENT = "DELETE FROM PAYMENTS WHERE PAYMENT_ID = ?";

    /**
     * Instance of global datasource to get connection from pool.
     */
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * return payment from database by specific id
     */
    @Override
    public Payment getPaymentById(int payment_id) {
        Payment payment = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PAYMENT_BY_ID)) {
            ps.setInt(1, payment_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                payment = parsePayment(resultSet);
                log.debug("Payment was received from database by id: " + payment_id);
            }
        } catch (SQLException e) {
            log.error("Failed to get payment by id: " + payment_id, e);
        } return payment;
    }

    /**
     * save payment to database
     */
    @Override
    public boolean savePayment(Payment payment) {
        int payment_id = payment.getId();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(payment_id != 0 ? UPDATE_PAYMENT : INSERT_PAYMENT)){
            ps.setString(1, payment.getType());
            ps.setInt(2, payment.getAmount());
            ps.setInt(3, Integer.parseInt(String.valueOf(payment.isPaid())));
            if (payment_id != 0){
                ps.setInt(4, payment_id);
            }
            ps.executeUpdate();
            log.debug("Payment was saved to database! Info: " + payment.toString());
        } catch (SQLException e) {
            log.error("Failed to save payment into database! Info: " + payment.toString(), e);
            return false;
        }
        return true;
    }

    /**
     * Parse cortege with payment credentials to object Payment.
     */
    private Payment parsePayment(ResultSet resultSet) {
        Payment payment = new Payment();
        try {
            payment.setId(resultSet.getInt(1));
            payment.setType(resultSet.getString(2));
            payment.setAmount(resultSet.getInt(3));
            payment.setPaid(Boolean.parseBoolean(String.valueOf(resultSet.getInt(3))));
        } catch (SQLException e) {
            log.error("Parsing of payment was failed! ", e);
        } return payment;
    }

    /**
     * remove payment from database by id
     */
    @Override
    public boolean removePayment(int payment_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_PAYMENT)) {
            ps.setInt(1, payment_id);
            ps.executeUpdate();
            log.debug("Payment was deleted by id: " + payment_id);
        } catch (SQLException e) {
            log.error("Failed to delete payment by id: " + payment_id, e);
            return false;
        }
        return true;
    }
}
