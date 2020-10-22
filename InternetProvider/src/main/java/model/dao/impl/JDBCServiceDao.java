package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.ServiceDao;
import model.entity.Service;
import model.entity.User;

public class JDBCServiceDao implements ServiceDao {

    private Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }


	@Override
	public void create(Service entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Service findById(int id) {
		Service service = null;
    	String sql= "Select * from services where services.id = ?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				int servId = resultSet.getInt(1);
				String name = resultSet.getString(2);
				service = new Service(name);
				service.setId(servId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return service;
	}

	@Override
	public void update(Service entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(int id) {
		
		
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Service> findAll() {
		List<Service> list = new ArrayList<>();
    	Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from services order by id;");
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Service service = new Service(name);
				service.setId(id);
				list.add(service);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

}
