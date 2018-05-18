package com.yura.lampak.store.model.impls;

import com.yura.lampak.store.model.entities.ProductAttribute;
import com.yura.lampak.store.model.dao.CategoryAttributeDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class CategoryAttributeDAOImpl implements CategoryAttributeDAO {

    private static final Logger log = LogManager.getLogger(CategoryAttributeDAOImpl.class);

    private static final String GET_ATTRIBUTE_BY_ID = "SELECT * FROM PRODUCTS_ATTRIBUTES WHERE ATTRIBUTE_ID = ?";
    private static final String GET_ATTRIBUTES_FOR_CATEGORY = "SELECT * FROM PRODUCTS_ATTRIBUTES WHERE PRODUCT_ID = ?";

    /**
     * Parameters in this order: PRODUCT_ID, ATTRIBUTE_NAME
     */
    private static final String INSERT_ATTRIBUTE = "INSERT INTO PRODUCTS_ATTRIBUTES VALUES (DEFAULT, ?, ?)";
    private static final String UPDATE_FEEDBACK = "UPDATE feedback SET product_id=?, attribute_name=?, WHERE attr_id=?";
    private static final String DELETE_ATTRIBUTE = "DELETE FROM PRODUCTS_ATTRIBUTES WHERE ATTRIBUTE_ID = ?";

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
    public Collection<ProductAttribute> getAttributesForCategory(int category_id) {
        Collection<ProductAttribute> productAttributes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ATTRIBUTES_FOR_CATEGORY)){
            ps.setInt(1, category_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                productAttributes.add(parseProdAttr(resultSet));
            } log.info("Attribute list was received from database for category_id: " + category_id);
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
                log.info("Attribute was received from database by id: " + attr_id);
            }
        } catch (SQLException e) {
            log.info(" Failed to receive attribute from database by id: " + attr_id, e);
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
    public void saveAttribute(ProductAttribute attribute) {
        int attr_id = attribute.getAttrId();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(attr_id != 0 ? UPDATE_FEEDBACK : INSERT_ATTRIBUTE)){
            ps.setInt(1, attribute.getProductId());
            ps.setString(2, attribute.getName());
            if (attr_id != 0){
                ps.setInt(3, attr_id);
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info("attribute was saved to database! Info: " + attribute.toString());
            }
        } catch (SQLException e) {
            log.error("Failed to save attribute to database! Info: " + attribute.toString(), e);
        }
    }

    /**
     * Remove attribute from database by specific id.
     */
    @Override
    public void removeAttribute(int attr_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ATTRIBUTE)) {
            ps.setInt(1, attr_id);
            if (ps.executeUpdate() > 0){
                log.info("Attribute was deleted by id: " + attr_id);
            }
        } catch (SQLException e) {
            log.error("Failed to delete attribute by id: " + attr_id, e);
        }
    }
}
