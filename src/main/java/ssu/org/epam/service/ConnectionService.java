package ssu.org.epam.service;

import org.springframework.stereotype.Service;
import ssu.org.epam.db.DbProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class ConnectionService {

    private Connection connection;

    @PostConstruct
    void setDbProperties() throws SQLException {
        connection = DriverManager.getConnection(DbProperties.URL, DbProperties.LOGIN, DbProperties.PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    @PreDestroy
    void closeConnection() throws SQLException {
        connection.close();
    }
}
