package model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.TarifDao;
import model.entity.Service;
import model.entity.Tarif;

public class JDBCTarifDao implements TarifDao{
	private Connection connection;

    public JDBCTarifDao(Connection connection) {
        this.connection = connection;
    }
    
	@Override
	public void create(Tarif entity) {
		String sql= "Insert into tarifs (name, description, price, service_id) Values (?,?,?,?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		preparedStatement.setString(1,entity.getName());
    		preparedStatement.setString(2,entity.getDescription());
    		preparedStatement.setBigDecimal(3,entity.getPrice());
    		preparedStatement.setInt(4,entity.getService().getId());
    		preparedStatement.executeUpdate();
    		
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Tarif findById(int id) {
		Tarif tarif = null;//Optional todo
    	String sql= "Select * from tarifs inner join services on tarifs.service_id = services.id where tarifs.id=?;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				int tarifId = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
				int servId  = resultSet.getInt(6);
				String servName  = resultSet.getString(7);
				Service service = new Service(servName);
				service.setId(servId);
				tarif = new Tarif(name, description, price);
				tarif.setId(tarifId);
				tarif.setService(service);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return tarif;
	}

	@Override
	public List<Tarif> findAll() {
		List<Tarif> list = new ArrayList<>();
    	Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from tarifs inner join services on tarifs.service_id = services.id;");
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
				int servId  = resultSet.getInt(6);
				String servName  = resultSet.getString(7);
				Service service = new Service(servName);
				service.setId(servId);
				Tarif tarif = new Tarif(name, description, price);
				tarif.setId(id);
				tarif.setService(service);
				list.add(tarif);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}
	
	public List<Tarif> findAllByServiceId(int serviceId) {
		List<Tarif> list = new ArrayList<>();
    	
    	String sql= "Select * from tarifs inner join services on tarifs.service_id = services.id where tarifs.service_id=?;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,serviceId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
				int servId  = resultSet.getInt(6);
				String servName  = resultSet.getString(7);
				Service service = new Service(servName);
				service.setId(servId);
				Tarif tarif = new Tarif(name, description, price);
				tarif.setId(id);
				tarif.setService(service);
				list.add(tarif);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}
	
	public List<Tarif> findSortedByNameASCWithServiceId(int serviceId) {
		List<Tarif> list = new ArrayList<>();
    	
    	String sql= "Select * from tarifs inner join services on tarifs.service_id = services.id where tarifs.service_id=? order by tarifs.name ASC;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,serviceId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
				int servId  = resultSet.getInt(6);
				String servName  = resultSet.getString(7);
				Service service = new Service(servName);
				service.setId(servId);
				Tarif tarif = new Tarif(name, description, price);
				tarif.setId(id);
				tarif.setService(service);
				list.add(tarif);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}
	public List<Tarif> findSortedByNameDESCWithServiceId(int serviceId) {
		List<Tarif> list = new ArrayList<>();
    	
    	String sql= "Select * from tarifs inner join services on tarifs.service_id = services.id where tarifs.service_id=? order by tarifs.name DESC;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,serviceId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
				int servId  = resultSet.getInt(6);
				String servName  = resultSet.getString(7);
				Service service = new Service(servName);
				service.setId(servId);
				Tarif tarif = new Tarif(name, description, price);
				tarif.setId(id);
				tarif.setService(service);
				list.add(tarif);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}
	public List<Tarif> findSortedByPriceWithServiceId(int serviceId) {
		List<Tarif> list = new ArrayList<>();
    	
    	String sql= "Select * from tarifs inner join services on tarifs.service_id = services.id where tarifs.service_id=? order by tarifs.price;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,serviceId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
				int servId  = resultSet.getInt(6);
				String servName  = resultSet.getString(7);
				Service service = new Service(servName);
				service.setId(servId);
				Tarif tarif = new Tarif(name, description, price);
				tarif.setId(id);
				tarif.setService(service);
				list.add(tarif);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	
	@Override
	public void update(Tarif entity) {
		String sql= "Update tarifs Set name=?, description=?, price=?, service_id=? Where tarifs.id=?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setString(1,(entity.getName()));
    		preparedStatement.setString(2,(entity.getDescription()));
    		preparedStatement.setBigDecimal(3,(entity.getPrice()));
    		preparedStatement.setInt(4,(entity.getService().getId()));
    		preparedStatement.setInt(5,entity.getId());
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql= "Delete from tarifs where tarifs.id = ?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,id);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
