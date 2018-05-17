package com.company.store.model.impls;

import com.company.store.model.dao.ProductParameterDAO;
import com.company.store.model.entities.ProductParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductParameterDAOImpl implements ProductParameterDAO {

    private static final Logger log = LogManager.getLogger(ProductParameterDAOImpl.class);

    private static final String GET_PARAMETER_BY_PRODUCT_AND_ATTR_ID = "SELECT * FROM PRODUCTS_PARAMETERS WHERE PRODUCT_ID = ? AND ATTR_ID = ?";

    /**
     * Parameters in this order: PRODUCT_ID, ATTR_ID, VALUE
     */
    private static final String INSERT_PARAMETER = "INSERT INTO PRODUCTS_PARAMETERS VALUES (?, ?, ?)";
    private static final String UPDATE_PARAMETER = "UPDATE products_parameters SET attr_id=?, value=? WHERE product_id=?";
    private static final String DELETE_PARAMETER_BY_PRODUCT_ID = "DELETE FROM PRODUCTS_PARAMETERS WHERE ATTRIBUTE_ID = ?";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProductParameterDAOImpl() {
    }

    /**
     * return parameter by id's of product and attribute.
     */
    @Override
    public ProductParameter getParamByProductAndAttrIds(int product_id, int attr_id) {
        ProductParameter prodParam = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PARAMETER_BY_PRODUCT_AND_ATTR_ID)) {
            ps.setInt(1, product_id);
            ps.setInt(2, attr_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                prodParam = parseProdParam(resultSet);
                log.info("success received parameter by product_id: " + product_id + ", attr_id: " + attr_id);
            }
        } catch (SQLException e){
            log.error("getting parameter by product_id and attr_id was failed! ", e);
        } return prodParam;
    }

    /**
     * parse parameter cortege to object ProductParameter.
     */
    private ProductParameter parseProdParam(ResultSet resultSet) {
        ProductParameter prodParameter = new ProductParameter();
        try {
            prodParameter.setProduct_id(resultSet.getInt(1));
            prodParameter.setAttr_id(resultSet.getInt(2));
            prodParameter.setValue(resultSet.getString(3));
        } catch (SQLException e) {
            log.error("Parsing of parameter was failed! ", e);
        } return prodParameter;
    }

    /**
     * save parameter of product into database
     */
    @Override
    public void saveParameter(ProductParameter productParam, boolean isUpdate) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(isUpdate ? UPDATE_PARAMETER : INSERT_PARAMETER)){
            if (isUpdate) {
                ps.setInt(1, productParam.getAttr_id());
                ps.setString(2, productParam.getValue());
                ps.setInt(3, productParam.getProduct_id());
            } else {
                ps.setInt(1, productParam.getProduct_id());
                ps.setInt(2, productParam.getAttr_id());
                ps.setString(3, productParam.getValue());
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info("Parameter was successfully saved into database! param: ", productParam.toString());
            }
        } catch (SQLException e) {
            log.error("Saving parameter into database was failed! param: " + productParam.toString(), e);
        }
    }

    /**
     * remove parameter of product from database
     */
    @Override
    public void removeParameterByProductId(int product_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_PARAMETER_BY_PRODUCT_ID)) {
            ps.setInt(1, product_id);
            if (ps.executeUpdate() > 0){
                log.info("parameter was deleted for product_id: " + product_id);
            }
        } catch (SQLException e) {
            log.error("Deleting parameter for product_id was failed: " + product_id + " failed! ", e);
        }
    }
}
