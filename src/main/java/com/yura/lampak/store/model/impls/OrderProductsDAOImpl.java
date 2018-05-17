package com.yura.lampak.store.model.impls;

import com.yura.lampak.store.model.entities.Order;
import com.yura.lampak.store.model.entities.OrderProduct;
import com.yura.lampak.store.model.entities.Product;
import com.yura.lampak.store.model.dao.OrderProductsDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class OrderProductsDAOImpl implements OrderProductsDAO {

    private static final Logger log = LogManager.getLogger(OrderProductsDAOImpl.class);

    private static final String GET_ORDER_PROD = "SELECT * FROM ORDER_PRODUCTS WHERE order_id = ?";

    private static final String INSERT_ORDER_PROD = "DECLARE\n" +
            "ord_id NUMBER;\n" +
            "prod_id NUMBER := ?;\n" +
            "amount NUMBER := ?;\n" +
            "price NUMBER := ?;\n" +
            "us_id NUMBER := ?;\n" +
            "BEGIN\n" +
            "    SELECT order_id\n" +
            "      INTO ord_id \n" +
            "      FROM ORDERS\n" +
            "      WHERE user_id = us_id\n" +
            "      AND order_date IS NULL;\n" +
            "    INSERT INTO ORDER_PRODUCTS VALUES(ord_id, prod_id, amount, price);\n" +
            "EXCEPTION\n" +
            "    WHEN NO_DATA_FOUND THEN\n" +
            "        INSERT INTO ORDERS (user_id) VALUES(us_id) RETURNING order_id INTO ord_id;\n" +
            "        INSERT INTO ORDER_PRODUCTS VALUES(ord_id, prod_id, amount, price);\n" +
            "END;";

    private static final String UPDATE_ORDER_PROD = "UPDATE ORDER_PRODUCTS SET product_id = ?, amount = ?, price = ? " +
            "WHERE order_id = ?";

    private static final String DELETE_ORDER_PRODUCT = "DELETE FROM ORDER_PRODUCTS WHERE product_id=? AND order_id = ?";
    private static final String DELETE_All_PRODS_FOR_ORDER = "DELETE FROM ORDER_PRODUCTS WHERE order_id = ?";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public OrderProductsDAOImpl() {
    }

    /**
     * Return collection of order products by specific order id.
     */
    @Override
    public Collection<OrderProduct> getOrderProductsById(int order_id) {
        Collection<OrderProduct> orderProducts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ORDER_PROD)){
            ps.setInt(1, order_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                orderProducts.add(parseOrderProd(resultSet));
            }
            log.info("Order products was received from database by order_id " + order_id);
        } catch (SQLException e) {
            log.error("Failed to receive order products by order_id: " + order_id, e);
        } return orderProducts;
    }

    /**
     * Parse cortege with order product credentials to object OrderProduct.
     */
    private OrderProduct parseOrderProd(ResultSet resultSet) {
        OrderProduct orderProd = new OrderProduct();
        try {
            orderProd.setOrder_id(resultSet.getInt(1));
            orderProd.setProduct_id(resultSet.getInt(2));
            orderProd.setAmount(resultSet.getInt(3));
            orderProd.setPrice(resultSet.getFloat(4));
        } catch (SQLException e) {
            log.error("Parsing of order product was failed! ", e);
        } return orderProd;
    }

    /**
     * Save product, it amount, price to database by specific order id
     */
    @Override
    public void saveProductToOrder(Product product, Order order, int amount, float price, boolean isUpdate) {
        int order_id = order.getId();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(isUpdate ? UPDATE_ORDER_PROD : INSERT_ORDER_PROD)){
            ps.setInt(1, product.getId());
            ps.setInt(2, amount);
            ps.setFloat(3, price);
            if (isUpdate) {
                ps.setInt(4, order_id);
            } else {
                ps.setInt(4, order.getUser_id());
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info("Order product was saved to DB by order_id " + order_id + " Info: " + product.toString() +
                        " Amount: " + amount + " Price: " + price);
            }
        } catch (SQLException e) {
            log.error("Failed to save order product! Product: " + product.toString() + " Order: " + order_id +
                    "Amount: " + amount + "Price: " + price, e);
        }
    }

    /**
     * Remove by one product from order by specific ids.
     */
    @Override
    public void removeProductFromOrder(int product_id, int order_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_PRODUCT)) {
            ps.setInt(1, product_id);
            ps.setInt(2, order_id);
            if (ps.executeUpdate() > 0){
                log.info("Order product was deleted by product_id: " + product_id + " and order_id: " + order_id);
            }
        } catch (SQLException e) {
            log.error("Failed to delete order product by prod_id: " + product_id + "and order_id:" + order_id, e);
        }
    }

    /**
     * Remove all products for specific order id.
     */
    @Override
    public void removeAllProductsFromOrder(int order_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_All_PRODS_FOR_ORDER)) {
            ps.setInt(1, order_id);
            if (ps.executeUpdate() > 0){
                log.info("Order products was deleted by order_id: " + order_id);
            }
        } catch (SQLException e) {
            log.error("Failed to delete order products by order_id:" + order_id, e);
        }
    }
}
