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

public class JDBCServiceDao implements ServiceDao {

    private Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }


	@Override
	public void create(Service entity) throws SQLException{
		String sql= "Insert into services (name) Values (?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		preparedStatement.setString(1,entity.getName());
    		preparedStatement.executeUpdate();
    		try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
    	} catch (SQLException e) {
    		throw new SQLException(e);
		}
		
	}

	@Override
	public Service findById(int id) throws SQLException{
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
			throw new SQLException(e);
		}
    	return service;
	}

	@Override
	public void update(Service entity) throws SQLException{
		String sql= "Update services Set name=? Where services.id=?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setString(1,(entity.getName()));
    		preparedStatement.setInt(2,entity.getId());
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		throw new SQLException(e);
		}
		
	}
	@Override
	public void delete(int id) throws SQLException{
		String sql= "Delete from services where services.id = ?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,id);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		throw new SQLException(e);
		}

		
	}

	@Override
	public void close() throws Exception {	
	}


	@Override
	public List<Service> findAll() throws SQLException {
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
			throw new SQLException();
		}
    	return list;
	}
}
