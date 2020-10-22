package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.dao.TarifDao;
import model.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {
    
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
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
	@Override
	public ServiceDao createServiceDao() {
		return new JDBCServiceDao(getConnection());
	}
	@Override
	public TarifDao createTarifDao() {
		return new JDBCTarifDao(getConnection());
	}
	
}
