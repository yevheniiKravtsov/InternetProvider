package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.dao.TarifDao;
import model.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {
    
    private Connection getConnection() throws SQLException, ClassNotFoundException{
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver"); 
    		return DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/internetprovider?allowPublicKeyRetrieval=true&serverTimezone=Europe/Kiev&useSSL=false",
                  "yevheniiKravtsov" ,
                 "ab1234ab" );
        }catch (SQLException e) {
        	throw new SQLException(e);
        
	    } catch (ClassNotFoundException e) {
	    	throw new ClassNotFoundException();
	    }
    }
    @Override
    public UserDao createUserDao() throws SQLException, ClassNotFoundException {
    	try {
    		return new JDBCUserDao(getConnection());
    	} catch (SQLException e) {
        	throw new SQLException(e);
	    } catch (ClassNotFoundException e) {
	    	throw new ClassNotFoundException();
	    }
    }
	@Override
	public ServiceDao createServiceDao() throws ClassNotFoundException, SQLException {
		try {
			return new JDBCServiceDao(getConnection());
		} catch (SQLException e) {
        	throw new SQLException(e);
	    } catch (ClassNotFoundException e) {
	    	throw new ClassNotFoundException();
	    }
	}
	@Override
	public TarifDao createTarifDao() throws ClassNotFoundException, SQLException {
		try {
			return new JDBCTarifDao(getConnection());
		} catch (SQLException e) {
	    	throw new SQLException(e);
	    } catch (ClassNotFoundException e) {
	    	throw new ClassNotFoundException();
	    }
	}
	
}
