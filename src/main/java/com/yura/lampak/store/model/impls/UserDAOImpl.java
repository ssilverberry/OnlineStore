package com.yura.lampak.store.model.impls;

import com.yura.lampak.store.model.ConnectionPool;
import com.yura.lampak.store.model.beans.User;
import com.yura.lampak.store.model.dao.UserDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDAOImpl implements UserDAO {

    private static final Logger log = LogManager.getLogger(UserDAOImpl.class);

    private static final String GET_ALL_USERS = "SELECT * FROM USERS";
    private static final String GET_USER_BY_ID = "SELECT * FROM USERS WHERE USER_ID = ?";
    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM USERS WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";

    /**
     * Parameters in this order: USER_NAME, USER_SURNAME, USER_EMAIL, USER_PHONE, USER_PASSWORD, USER_ADDRESS, ISADMIN
     */
    private static final String INSERT_USER = "INSERT INTO USERS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_CREDENTIALS = "UPDATE users SET user_name=?, user_surname=?, user_email=?, " +
            "user_phone=?, user_password=?, user_address=?, isadmin=? WHERE user_id=?";

    private static final String DELETE_USER = "DELETE FROM USERS WHERE USER_ID = ?";

    public UserDAOImpl() {
    }

    /**
     * parse cortege with user credentials to object User.
     */
    private User parseUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPhone(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setAddress(resultSet.getString(7));
            user.setIsAdmin(Boolean.parseBoolean(String.valueOf(resultSet.getInt(8))));
        } catch (SQLException e) {
            e.printStackTrace();
        } return user;
    }

    /**
     * save user credentials to database
     */
    @Override
    public void saveUser(User user) {
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(user.getId() != 0 ? UPDATE_USER_CREDENTIALS : INSERT_USER)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getAddress());
            ps.setInt(7, Integer.parseInt(String.valueOf(user.getIsAdmin())));
            if (user.getId() != 0){
                ps.setInt(8, user.getId());
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                log.info("User was successfully inserted into db!, Info: " + user.toString());
            }
        } catch (SQLException e) {
            log.error("Inserting user into db was failed! User: " + user.toString(), e);
        }
    }

    /**
     * return all users from database
     */
    @Override
    public Collection<User> getAllUsers() {
        Collection<User> users = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(parseUser(resultSet));
            } log.info("Successfully received all users from database!");
        } catch (SQLException e) {
            log.error("Receiving users from database was failed: ", e);
        }
        return users;
    }

    /**
     * return user from database by specify id
     */
    @Override
    public User getById(int user_id) {
        User user = null;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user = parseUser(resultSet);
                log.info("User was successfully received from database by id: " + user_id);
            }
        } catch (SQLException e) {
            log.error("Receiving user was failed, user_id: " + user_id, e);
        } return user;
    }

    /**
     * return user from database by specify credentials
     */
    @Override
    public User getByCredentials(String email, String password) {
        User user = null;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_CREDENTIALS)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user = parseUser(resultSet);
                log.info("Received a user by credentials, user: " + user.toString());
            }
        } catch (SQLException e) {
            log.error("Receiving user by credentials was failed, email: " + email, e);
        } return user;
    }

    /**
     * remove user from database by id
     */
    @Override
    public void removeUser(int user_id) {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setInt(1, user_id);
            if (ps.executeUpdate() > 0){
                log.info("user was deleted by id: " + user_id);
            }
        } catch (SQLException e) {
            log.error("deleting user was failed, id: " + user_id, e);
        }
    }
}
