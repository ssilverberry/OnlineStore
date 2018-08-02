package com.company.store.model.repository.impl;


import com.company.store.model.repository.ProductDAO;
import com.company.store.model.entities.Product;
import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.entities.ProductParameter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class ProductDAOImpl implements ProductDAO {

    private static final Logger log = LogManager.getLogger(ProductDAOImpl.class);

    private static final String GET_CATEGORIES = "SELECT * FROM PRODUCTS WHERE ISCATEGORY = 1 AND PARENT_ID IS null " +
            "OR PARENT_ID=0";
    private static final String GET_SUBCATEGORIES = "SELECT * FROM PRODUCTS WHERE ISCATEGORY = 1 AND PARENT_ID > 0";

    private static final String GET_PRODUCTS_FOR_CATEGORY = "SELECT * FROM PRODUCTS WHERE PARENT_ID = ?";

    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?";
    private static final String GET_PRODUCT_BY_NAME = "SELECT * FROM PRODUCTS WHERE product_name = ?";

    private static final String GET_PARAMS_FOR_PRODUCT = "SELECT PRODUCTS_PARAMETERS.attr_id, " +
            "PRODUCTS_ATTRIBUTES.attribute_name, PRODUCTS_PARAMETERS.value FROM PRODUCTS_PARAMETERS " +
            "INNER JOIN PRODUCTS_ATTRIBUTES ON PRODUCTS_ATTRIBUTES.attribute_id=PRODUCTS_PARAMETERS.attr_id " +
            "AND PRODUCTS_PARAMETERS.product_id = ?";
    /**
     * Parameters in this order: PARENT_ID, PRODUCT_NAME, ISCATEGORY
     */
    private static final String INSERT_PRODUCT = "INSERT INTO PRODUCTS VALUES (DEFAULT, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE products SET parent_id=?, product_name=?, iscategory=? " +
                                                                 "WHERE product_id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
    private static final String DELETE_CATEGORY_PRODUCTS = "DELETE FROM PRODUCTS WHERE parent_id=?";

    /**
     * Instance of global datasource to get connection from pool.
     */
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * return products from database table <tt>PRODUCTS</tt>, which have parameter "ISCATEGORY" true.
     */
    @Override
    public Collection<Product> getCategories() {
        Collection<Product> categories = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_CATEGORIES)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                categories.add(parseProduct(resultSet));
            } log.debug("Categories was received from database!");
        } catch (SQLException e) {
            log.error("Failed to receive categories from database! ", e);
        } return categories;
    }

    /**
     * returns subcategory from db
     */
    @Override
    public Collection<Product> getSubcategories() {
        Collection<Product> subcategories = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_SUBCATEGORIES)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                subcategories.add(parseProduct(resultSet));
            } log.debug("Subcategories was received from database!");
        } catch (SQLException e) {
            log.error("Failed to receive categories from database! ", e);
        } return subcategories;
    }

    /**
     * return products for specify category
     */
    @Override
    public Collection<Product> getProductsForCategory(int category_id) {
        Collection<Product> products = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PRODUCTS_FOR_CATEGORY)) {
            ps.setInt(1, category_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                products.add(parseProduct(resultSet));
            } log.debug("Products of category was received from database by id: " + category_id);
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
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            ps.setInt(1, product_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                product = parseProduct(resultSet);
                log.debug("Product was received  by id: " + product_id);
            }
        } catch (SQLException e) {
            log.error("Failed to receive product by id: " + product_id, e);
        } return product;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                product = parseProduct(resultSet);
                log.debug("Product was received  by name: " + name);
            }
        } catch (SQLException e) {
            log.error("Failed to receive product by name: " + name, e);
        } return product;
    }

    /**
     * return parameters for product by specify product_id
     */
    @Override
    public Map<ProductAttribute, ProductParameter> getParamsForProduct(int product_id) {
        Map<ProductAttribute, ProductParameter> prodParams = new TreeMap<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PARAMS_FOR_PRODUCT)) {
            ps.setInt(1, product_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int attr_id = resultSet.getInt(1);
                ProductAttribute prodAttr = new ProductAttribute(attr_id, product_id, resultSet.getString(2));
                ProductParameter prodParam = new ProductParameter(attr_id, product_id, resultSet.getString(3));
                prodParams.put(prodAttr, prodParam);
            } log.debug("Parameters was received for product_id: " + product_id);
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
                product.setParentId(resultSet.getInt("parent_id"));
                product.setParameters(null);
            } else {
                product.setCategory(false);
                product.setId(product_id);
                product.setName(productName);
                product.setParentId(parent_id);
                product.setParameters(getParamsForProduct(product_id));
            }
        } catch (SQLException e) {
            log.error("Parsing of product was failed! ", e);
        } return product;
    }

    /**
     * Save product into database
     */
    @Override
    public boolean saveProduct(Product product) {
        int product_id = product.getId();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(product_id != 0 ? UPDATE_PRODUCT : INSERT_PRODUCT)){
             ps.setInt(1, product.getParentId());
             ps.setString(2, product.getName());
             if (!product.isCategory())
                 ps.setInt(3, 0);
             else
                 ps.setInt(3, 1);
             if (product_id != 0)
                 ps.setInt(4, product_id);
             ps.executeUpdate();
             log.debug(" Product was saved to database! Info: " + product.toString());
        } catch (SQLException e) {
            log.error("Failed to save product to database! Info: " + product.toString(), e);
            return false;
        }
        return true;
    }

    /**
     * remove product from database by specify id
     */
    @Override
    public boolean removeProduct(int product_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT)) {
            ps.setInt(1, product_id);
            ps.executeUpdate();
            log.debug("Product was deleted by id: " + product_id);
        } catch (SQLException e) {
            log.error("Failed to delete product by id: " + product_id, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCategoryProducts(int category_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY_PRODUCTS)) {
            ps.setInt(1, category_id);
            ps.executeUpdate();
            log.debug("Products was deleted by category_id: " + category_id);
        } catch (SQLException e) {
            log.error("Failed to delete products by category_id: " + category_id, e);
            return false;
        }
        return true;
    }
}
