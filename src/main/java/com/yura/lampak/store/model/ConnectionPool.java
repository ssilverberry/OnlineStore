package com.yura.lampak.store.model;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

public class ConnectionPool {

    private PoolDataSource pds;
    private static ConnectionPool instance;
    private static final String pathToDBProperties = "resources/db.properties";
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);

    /**
     * Reads params for connect to database from properties file
     * and constructs pool of connections with specify size
     */
    private ConnectionPool() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(pathToDBProperties));
            pds = PoolDataSourceFactory.getPoolDataSource();
            pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            pds.setURL(props.getProperty("Url"));
            pds.setUser(props.getProperty("User"));
            pds.setPassword(props.getProperty("Password"));
            pds.setInitialPoolSize(5);
            pds.setMaxPoolSize(20);
            log.info("Success connection to DB! " + " Pool was configured!");
        } catch (SQLException | IOException e) {
            log.error("Exception on connection to DB, ", e);
        }
    }

    /**
     * Return instance of ConnectionPool
     * @return
     */
    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    /**
     * Method for get connection from pool.
     *
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = pds.getConnection();
            log.info("Connection from pool was got, pool size: " + pds.getAvailableConnectionsCount());
        } catch (SQLException e) {
            log.info("got connection from pool, pool size: ", e);
        } return connection;
    }
}
