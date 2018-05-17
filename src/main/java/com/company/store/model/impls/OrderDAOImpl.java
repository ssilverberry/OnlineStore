package com.company.store.model.impls;

import com.company.store.model.dao.OrderDAO;
import com.company.store.model.entities.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger log = LogManager.getLogger(OrderDAOImpl.class);

    private static final String GET_ALL_ORDERS = "SELECT * FROM ORDERS";
    private static final String GET_ALL_ORDERS_FOR_USER = "SELECT * FROM ORDERS WHERE USER_ID = ?";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";

    /**
     * Parameters in this order: ORDER_DATE, USER_ID, PAYMENT_ID, DELIVERY_ID
     */
    private static final String INSERT_ORDER = "INSERT INTO ORDERS VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET order_date=?, user_id=?, payment_id=?, delivery_id=? WHERE order_id=?";
    private static final String DELETE_ORDER = "DELETE FROM ORDERS WHERE ORDER_ID = ?";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public OrderDAOImpl() {
    }

    /**
     * Return order from database by specify id.
     */
    @Override
    public Order getOrderById(int order_id) {
        Order order = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)){
            ps.setInt(1, order_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                order = parseOrder(resultSet);
                log.info("Order was received  by id: " + order_id);
            }
        } catch (SQLException e) {
            log.error("Failed to receive order by id: " + order_id, e);
        }
        return order;
    }

    /**
     * Parse cortege with order credentials to object Order.
     */
    private Order parseOrder(ResultSet resultSet){
        Order order = new Order();
        try {
            order.setId(resultSet.getInt(1));
            order.setDate(resultSet.getTimestamp(2));
            order.setUser_id(resultSet.getInt(3));
            order.setPayment_id(resultSet.getInt(4));
            order.setDelivery_id(resultSet.getInt(5));
        } catch (SQLException e) {
            log.error("Parsing of order was failed! ", e);
        } return order;
    }

    /**
     * Return collection of orders for user by specify id.
     */
    @Override
    public Collection<Order> getAllOrdersForUser(int user_id) {
        Collection<Order> orders = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS_FOR_USER)){
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                orders.add(parseOrder(resultSet));
                log.info("Orders was received from database for user by id: " + user_id);
            }
        } catch (SQLException e) {
            log.error("Failed to receive orders for user by id: " + user_id, e);
        }
        return orders;
    }

    /**
     * Return collection of all orders.
     */
    @Override
    public Collection<Order> getAllOrders() {
        Collection<Order> orders = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                orders.add(parseOrder(resultSet));
            } log.info("All orders was received from database! ");
        } catch (SQLException e) {
            log.error("Failed to receive all orders from database! ", e);
        }
        return orders;
    }

    /**
     * Save order to database.
     */
    @Override
    public void saveOrder(Order order) {
        int order_id = order.getId();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(order_id != 0 ? UPDATE_ORDER : INSERT_ORDER)){
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2, order.getUser_id());
            ps.setInt(3, order.getPayment_id());
            ps.setInt(4, order.getDelivery_id());
            if (order_id != 0){
                ps.setInt(5, order_id);
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info("Order was saved to database! Info: " + order.toString());
            }
        } catch (SQLException e) {
            log.error("Failed to save order into database! Info: " + order.toString(), e);
        }
    }

    /**
     * Remove order by specific id.
     */
    @Override
    public void removeOrderById(int order_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDER)) {
            ps.setInt(1, order_id);
            if (ps.executeUpdate() > 0){
                log.info("Order was deleted by id: " + order_id);
            }
        } catch (SQLException e) {
            log.error("Failed to delete order by id: " + order_id, e);
        }
    }
}
