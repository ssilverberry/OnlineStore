package com.yura.lampak.store.model.impls;

import com.yura.lampak.store.model.entities.Delivery;
import com.yura.lampak.store.model.dao.DeliveryDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class DeliveryDAOImpl implements DeliveryDAO {

    private static final Logger log = LogManager.getLogger(DeliveryDAOImpl.class);

    private static final String GET_ALL_DELIVERIES = "SELECT * FROM DELIVERIES";
    private static final String GET_DELIVERY_BY_ID = "SELECT * FROM DELIVERIES WHERE DELIVERY_ID = ?";

    /**
     * Parameters in this order: RECEIVER_NAME, RECEIVER_SURNAME, ADDRESS, PHONE, STATUS
     */
    private static final String INSERT_DELIVERY = "INSERT INTO DELIVERIES VALUES (DEFAULT, ?, ?, ?, ?, ?)";

    private static final String UPDATE_DELIV_CREDENTIALS = "UPDATE deliveries SET receiver_name=?, receiver_surname=?, " +
            "address=?, phone=?, status=? WHERE delivery_id = ?";

    private static final String UPDATE_STATUS = "UPDATE DELIVERIES SET STATUS = ? WHERE DELIVERY_ID = ?";

    private static final String DELETE_DELIVERY = "DELETE FROM DELIVERIES WHERE DELIVERY_ID = ?";

    /**
     * Instance of global datasource to get connection from pool.
     */
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Return all deliveries, which stores in database.
     */
    @Override
    public Collection<Delivery> getAllDeliveries() {
        Collection<Delivery> deliveries = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_DELIVERIES)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                deliveries.add(parseDelivery(resultSet));
            } log.info("All deliveries was received from database!");
        } catch (SQLException e) {
            log.error("Failed to receive all deliveries! ", e);
        }
        return deliveries;
    }

    /**
     *
     * Return delivery by specific id.
     */
    @Override
    public Delivery getDeliveryById(int delivery_id) {
        Delivery delivery = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_DELIVERY_BY_ID)) {
            ps.setInt(1, delivery_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                delivery = parseDelivery(resultSet);
                log.info("Got delivery by id: " + delivery_id);
            } else {
                log.info("delivery doesn't exist by id: " + delivery_id);
            }
        } catch (SQLException e) {
            log.error("got delivery failed, delivery_id: " + delivery_id, e);
        } return delivery;
    }

    /**
     * Save delivery to database.
     */
    @Override
    public void saveDelivery(Delivery delivery) {
        int deliv_id = delivery.getId();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deliv_id != 0 ? UPDATE_DELIV_CREDENTIALS : INSERT_DELIVERY)){
            ps.setString(1, delivery.getReceiverName());
            ps.setString(2, delivery.getReceiverSurname());
            ps.setString(3, delivery.getAddress());
            ps.setString(4, delivery.getReceiverPhone());
            ps.setString(5, delivery.getStatus());
            if (deliv_id != 0){
                ps.setInt(6, deliv_id);
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info("Delivery was saved to database! Info:" + delivery.toString());
            }
        } catch (SQLException e) {
            log.error("Failed to save delivery into database! Info:" + delivery.toString(), e);
        }
    }

    /**
     * Update status of delivery by specific id.
     */
    @Override
    public void updateStatus(int delivery_id, String status) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS)){
            ps.setString(1, status);
            ps.setInt(2, delivery_id);
            if (ps.executeUpdate() > 0) {
                log.info("Delivery status was updated by id: " + delivery_id);
            }
        } catch (SQLException e) {
            log.error("Failed to update delivery status by id: " + delivery_id, e);
        }
    }

    /**
     * Parse cortege with delivery credentials to object Delivery.
     */
    private Delivery parseDelivery(ResultSet resultSet) {
        Delivery delivery = new Delivery();
        try {
            delivery.setId(resultSet.getInt(1));
            delivery.setReceiverName(resultSet.getString(2));
            delivery.setReceiverSurname(resultSet.getString(3));
            delivery.setAddress(resultSet.getString(4));
            delivery.setReceiverPhone(resultSet.getString(5));
            delivery.setStatus(resultSet.getString(6));
        } catch (SQLException e) {
            log.error("Parsing of delivery was failed! ", e);
        } return delivery;
    }

    /**
     * Remove delivery from database by specific id.
     */
    @Override
    public void removeDelivery(int delivery_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_DELIVERY)) {
            ps.setInt(1, delivery_id);
            if (ps.executeUpdate() > 0){
                log.info("delivery was deleted by id: " + delivery_id);
            }
        } catch (SQLException e) {
            log.error("Failed to delete delivery by id: " + delivery_id, e);
        }
    }

}
