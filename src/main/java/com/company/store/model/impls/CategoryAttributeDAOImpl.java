package com.company.store.model.impls;

import com.company.store.model.entities.ProductAttribute;
import com.company.store.model.dao.CategoryAttributeDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryAttributeDAOImpl implements CategoryAttributeDAO {

    private static final Logger log = LogManager.getLogger(CategoryAttributeDAOImpl.class);

    private static final String GET_ATTRIBUTE_BY_ID = "SELECT * FROM PRODUCTS_ATTRIBUTES WHERE ATTRIBUTE_ID = ?";
    private static final String GET_ATTRIBUTE_BY_NAME = "SELECT * FROM PRODUCTS_ATTRIBUTES WHERE ATTRIBUTE_NAME = ?";
    private static final String GET_ATTRIBUTES_FOR_CATEGORY = "SELECT * FROM PRODUCTS_ATTRIBUTES WHERE PRODUCT_ID = ?";

    /**
     * Parameters in this order: PRODUCT_ID, ATTRIBUTE_NAME
     */
    private static final String INSERT_ATTRIBUTE = "INSERT INTO PRODUCTS_ATTRIBUTES VALUES (DEFAULT, ?, ?)";
    private static final String UPDATE_ATTRIBUTE = "UPDATE PRODUCTS_ATTRIBUTES SET attribute_name=? WHERE attribute_id=?";
    private static final String DELETE_ATTRIBUTE = "DELETE FROM PRODUCTS_ATTRIBUTES WHERE ATTRIBUTE_ID = ?";

    private static final String DELETE_CATEGORY_ATTRIBUTES = "DELETE FROM PRODUCTS_ATTRIBUTES WHERE product_id=?";

    /**
     * Instance of global datasource to get connection from pool.
     */
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Return attributes for specific category by id.
     */
    @Override
    public List<ProductAttribute> getAttributesForCategory(int category_id) {
        List<ProductAttribute> productAttributes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ATTRIBUTES_FOR_CATEGORY)){
            ps.setInt(1, category_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                productAttributes.add(parseProdAttr(resultSet));
            } log.debug("Attribute list was received from database for category_id: " + category_id);
        } catch (SQLException e) {
            log.error("Failed to receive list of attributes for category_id: " + category_id, e);
        } return productAttributes;
    }

    /**
     * Return attribute by specific id.
     */
    @Override
    public ProductAttribute getAttributeById(int attr_id) {
        ProductAttribute productAttribute = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ATTRIBUTE_BY_ID)){
            ps.setInt(1,attr_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                productAttribute = parseProdAttr(resultSet);
                log.debug("Attribute was received from database by id: " + attr_id);
            }
        } catch (SQLException e) {
            log.info(" Failed to receive attribute from database by id: " + attr_id, e);
        }
        return productAttribute;
    }

    @Override
    public ProductAttribute getAttributeByName(String attrName) {
        ProductAttribute productAttribute = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ATTRIBUTE_BY_NAME)){
            ps.setString(1,attrName);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                productAttribute = parseProdAttr(resultSet);
                log.debug("Attribute was received from database by name: " + attrName);
            }
        } catch (SQLException e) {
            log.info(" Failed to receive attribute from database by name: " + attrName, e);
        }
        return productAttribute;
    }

    /**
     * Parse cortege with attribute credentials to object ProductAttribute.
     */
    private ProductAttribute parseProdAttr(ResultSet resultSet) {
        ProductAttribute prodAttr = new ProductAttribute();
        try {
            prodAttr.setAttrId(resultSet.getInt(1));
            prodAttr.setProductId(resultSet.getInt(2));
            prodAttr.setName(resultSet.getString(3));
        } catch (SQLException e) {
            log.error("Parsing of attribute was failed! ", e);
        } return prodAttr;
    }

    /**
     * Save attribute to database.
     */
    @Override
    public boolean saveAttribute(ProductAttribute attribute) {
        int attr_id = attribute.getAttrId();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(attr_id != 0 ? UPDATE_ATTRIBUTE : INSERT_ATTRIBUTE)){
            if (attr_id != 0){
                ps.setString(1, attribute.getName());
                ps.setInt(2, attr_id);
            } else {
                ps.setInt(1, attribute.getProductId());
                ps.setString(2, attribute.getName());
            }
            ps.executeUpdate();
            log.debug("attribute was saved to database! Info: " + attribute.toString());
        } catch (SQLException e) {
            log.error("Failed to save attribute to database! Info: " + attribute.toString(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveAttributes(List<ProductAttribute> attributes, boolean isUpdate) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(isUpdate ? UPDATE_ATTRIBUTE : INSERT_ATTRIBUTE)){
            connection.setAutoCommit(false);
            for (ProductAttribute attribute : attributes) {
                if (isUpdate){
                    ps.setString(1, attribute.getName());
                    ps.setInt(2, attribute.getAttrId());
                } else {
                    ps.setInt(1, attribute.getProductId());
                    ps.setString(2, attribute.getName());
                }
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            log.debug("attributes was saved to database! param: " + attributes.toString());
        } catch (SQLException e) {
            log.error("Failed to save attributes to database! Info: " + attributes.toString(), e);
            return false;
        }
        return true;
    }

    /**
     * Remove attribute from database by specific id.
     */
    @Override
    public boolean removeAttribute(int attr_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ATTRIBUTE)) {
            ps.setInt(1, attr_id);
            ps.executeUpdate();
            log.debug("Attribute was deleted by id: " + attr_id);
        } catch (SQLException e) {
            log.error("Failed to delete attribute by id: " + attr_id, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCategoryAttributes(int category_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY_ATTRIBUTES)) {
            ps.setInt(1, category_id);
            ps.executeUpdate();
            log.debug("Attributes was deleted by category_id: " + category_id);
        } catch (SQLException e) {
            log.error("Failed to delete attributes by category_id: " + category_id, e);
            return false;
        }
        return true;
    }


}
