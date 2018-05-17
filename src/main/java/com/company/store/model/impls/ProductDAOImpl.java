package com.company.store.model.impls;

import com.company.store.model.ConnectionPool;
import com.company.store.model.beans.Product;
import com.company.store.model.beans.ProductAttribute;
import com.company.store.model.beans.ProductParameter;
import com.company.store.model.dao.ProductDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductDAOImpl implements ProductDAO {

    private static final Logger log = LogManager.getLogger(ProductDAOImpl.class);

    private static final String GET_CATEGORIES = "SELECT * FROM PRODUCTS WHERE ISCATEGORY = 1";

    private static final String GET_PRODUCTS_FOR_CATEGORY = "SELECT * FROM PRODUCTS WHERE PARENT_ID = ?";

    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?";

    private static final String GET_PARAMS_FOR_PRODUCT = "SELECT PRODUCTS_PARAMETERS.attr_id, " +
            "PRODUCTS_ATTRIBUTES.attribute_name, PRODUCTS_PARAMETERS.value FROM PRODUCTS_PARAMETERS " +
            "INNER JOIN PRODUCTS_ATTRIBUTES ON PRODUCTS_ATTRIBUTES.attribute_id=PRODUCTS_PARAMETERS.attr_id " +
            "AND PRODUCTS_PARAMETERS.product_id = ?";
    /**
     * Parameters in this order: PARENT_ID, PRODUCT_NAME, ISCATEGORY
     */
    private static final String INSERT_PRODUCT = "INSERT INTO PRODUCTS VALUES (DEFAULT, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE products SET parent_id=?, product_name=?, iscategory=? WHERE product_id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";

    public ProductDAOImpl() {
    }

    /**
     * return products from database table <tt>PRODUCTS</tt>, which have parameter "ISCATEGORY" true.
     */
    @Override
    public Collection<Product> getCategories() {
        Collection<Product> categories = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_CATEGORIES)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                categories.add(parseProduct(resultSet));
            } log.info("Categories was received from database!");
        } catch (SQLException e) {
            log.error("Failed to receive categories from database! ", e);
        } return categories;
    }

    /**
     * return products for specify category
     */
    @Override
    public Collection<Product> getProductsForCategory(int category_id) {
        Collection<Product> products = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PRODUCTS_FOR_CATEGORY)) {
            ps.setInt(1, category_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                products.add(parseProduct(resultSet));
            } log.info("Products of category was received from database by id: " + category_id);
        } catch (SQLException e) {
            log.error("Failed to receive products for category by id: " + category_id);
        } return products;
    }

    /**
     * return product by specify id
     */
    @Override
    public Product getProductById(int product_id) {
        Product product = null;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            ps.setInt(1, product_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                product = parseProduct(resultSet);
                log.info("Product was received  by id: " + product_id);
            }
        } catch (SQLException e) {
            log.error("Failed to receive product by id: " + product_id, e);
        } return product;
    }

    /**
     * return parameters for product by specify product_id
     */
    @Override
    public Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id) {
        Map<ProductAttribute, ProductParameter> prodParams = new HashMap<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PARAMS_FOR_PRODUCT)) {
            ps.setInt(1, product_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int attr_id = resultSet.getInt(1);
                ProductAttribute prodAttr = new ProductAttribute(attr_id, product_id, resultSet.getString(2));
                ProductParameter prodParam = new ProductParameter(attr_id, product_id, resultSet.getString(3));
                prodParams.put(prodAttr, prodParam);
            } log.info("Parameters was received for product_id: " + product_id);
        } catch (SQLException e){
            log.error("Failed to receive product by id: " + product_id, e);
        } return prodParams;
    }

    /**
     * parse cortege with product from database to object Product.
     */
    private Product parseProduct(ResultSet resultSet) {
        Product product = new Product();
        try {
            int product_id = resultSet.getInt(1);
            int parent_id = resultSet.getInt(2);
            String productName = resultSet.getString(3);
            if (resultSet.getInt(4) == 1){
                product.setCategory(true);
                product.setId(product_id);
                product.setName(productName);
                product.setParent_id(0);
                product.setParameters(null);
            } else {
                product.setCategory(false);
                product.setId(product_id);
                product.setName(productName);
                product.setParent_id(parent_id);
                product.setParameters(getParamsForProduct(product_id));
            }
        } catch (SQLException e) {
            log.error("Parsing of product was failed! ", e);
        } return product;
    }

    /**
     * save product into database
     */
    @Override
    public void saveProduct(Product product) {
        int product_id = product.getId();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(product_id != 0 ? UPDATE_PRODUCT : INSERT_PRODUCT)){
            ps.setInt(1, product.getParent_id());
            ps.setString(2, product.getName());
            ps.setInt(3, Integer.parseInt(String.valueOf(product.isCategory())));
            if (product_id != 0){
                ps.setInt(4, product_id);
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info(" Product was saved to database! Info: " + product.toString());
            }
        } catch (SQLException e) {
            log.error("Failed to save product to database! Info: " + product.toString(), e);
        }
    }

    /**
     * remove product from database by specify id
     */
    @Override
    public void removeProduct(int product_id) {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT)) {
            ps.setInt(1, product_id);
            if (ps.executeUpdate() > 0){
                log.info("Product was deleted by id: " + product_id);
            }
        } catch (SQLException e) {
            log.error("Failed to delete product by id: " + product_id, e);
        }
    }
}
