package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.dao.DaoFactory;
import model.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
    private Connection getConnection(){
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/internetprovider?allowPublicKeyRetrieval=true&serverTimezone=Europe/Kiev&useSSL=false",
                    "yevheniiKravtsov" ,
                    "ab1234ab" );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
	
}
