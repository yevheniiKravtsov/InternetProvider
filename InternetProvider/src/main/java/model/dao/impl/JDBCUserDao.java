package model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dao.UserDao;
import model.entity.Service;
import model.entity.Tarif;
import model.entity.User;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

	@Override
	public void create(User entity) throws SQLException{
		String sql= "Insert into users (login, password, role, confirmation, blocked, account) Values (?,?,?,?,?,?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		preparedStatement.setString(1,entity.getLogin());
    		preparedStatement.setString(2,entity.getPassword());
    		preparedStatement.setString(3,entity.getRole().toString());
    		preparedStatement.setBoolean(4,entity.getIsConfirmed());
    		preparedStatement.setBoolean(5,entity.getIsBlocked());
    		preparedStatement.setBigDecimal(6,entity.getAccount());
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
    		throw new SQLException();
		}		
	}

	
	public void insertIntoUsersTarifs(int userId, int tarifId) throws SQLException{
		String sql= "Insert into users_tarifs (user_id, tarif_id) Values (?,?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		preparedStatement.setInt(1,userId);
    		preparedStatement.setInt(2,tarifId);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		throw new SQLException();
		}		
	}
	
	public void removeFromUsersTarifs(int userId, int tarifId) throws SQLException{
		String sql= "Delete from users_tarifs where user_id = ? AND tarif_id=?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,userId);
    		preparedStatement.setInt(2,tarifId);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		throw new SQLException(e);
		}
		
	}
	public User findTarifsForUser(User user) throws SQLException{
		
		List<Tarif> list = new ArrayList<>();
    	String sql= "select * from users_tarifs inner join tarifs on users_tarifs.tarif_id =tarifs.id\r\n" + 
    			" inner join services on tarifs.service_id= services.id where user_id = ?;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(3);
				String name = resultSet.getString(4);
				String description = resultSet.getString(5);
				BigDecimal price = resultSet.getBigDecimal(6);
				int servId  = resultSet.getInt(8);
				String servName  = resultSet.getString(9);
				Service service = new Service(servName);
				service.setId(servId);
				Tarif tarif = new Tarif(name, description, price);
				tarif.setId(id);
				tarif.setService(service);
				list.add(tarif);
			}
			user.setTarifList(list);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
    	return user;
	}
	
	@Override
	public User findById(int id) throws SQLException{
		User user = null;
    	String sql= "Select * from users where users.id = ?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				int userId = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				User.ROLE role = User.ROLE.valueOf(resultSet.getString(4));
				boolean isConfirmed = resultSet.getBoolean(5);
				boolean isBlocked = resultSet.getBoolean(6);
				BigDecimal account = resultSet.getBigDecimal(7);
				user = new User(login, password, role,isConfirmed);
				user.setId(userId);
				user.setAccount(account);
				user.setIsBlocked(isBlocked);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
    	return user;
	}
	
	public List<User> findAllByRole(User.ROLE role) throws SQLException{
		List<User> list = new ArrayList<>();
    	String sql= "Select * from users where users.role = ? order by id DESC";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setString(1,role.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				User.ROLE userRole = User.ROLE.valueOf(resultSet.getString(4));
				boolean isConfirmed = resultSet.getBoolean(5);
				boolean isBlocked = resultSet.getBoolean(6);
				BigDecimal account = resultSet.getBigDecimal(7);
				User user = new User(login, password, userRole, isConfirmed);
				user.setId(id);
				user.setAccount(account);
				user.setIsBlocked(isBlocked);
				list.add(user);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
    	return list;
	}
	
	public List<User> findAllByConfirmation(boolean confirmation) throws SQLException{
		List<User> list = new ArrayList<>();
    	String sql= "Select * from users where users.confirmation = ?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setBoolean(1,confirmation);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				User.ROLE userRole = User.ROLE.valueOf(resultSet.getString(4));
				boolean isConfirmed = resultSet.getBoolean(5);
				boolean isBlocked = resultSet.getBoolean(6);
				BigDecimal account = resultSet.getBigDecimal(7);
				User user = new User(login, password, userRole, isConfirmed);
				user.setId(id);
				user.setAccount(account);
				user.setIsBlocked(isBlocked);
				list.add(user);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
    	return list;
	}

	@Override
	public List<User> findAll() throws SQLException{
		List<User> list = new ArrayList<>();
    	Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from users order by id;");
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				User.ROLE role = User.ROLE.valueOf(resultSet.getString(4));
				boolean isConfirmed = resultSet.getBoolean(5);
				boolean isBlocked = resultSet.getBoolean(6);
				BigDecimal account = resultSet.getBigDecimal(7);
				User user = new User(login, password, role, isConfirmed);
				user.setId(id);
				user.setAccount(account);
				user.setIsBlocked(isBlocked);
				list.add(user);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
    	return list;
	}

	@Override
	public void update(User entity) throws SQLException{
		String sql= "Update users Set login=?, password=?, role=?, confirmation=?, blocked=?,account=? Where users.id=?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setString(1,(entity.getLogin()));
    		preparedStatement.setString(2,(entity.getPassword()));
    		preparedStatement.setString(3,(entity.getRole().toString()));
    		preparedStatement.setBoolean(4,(entity.getIsConfirmed()));
    		preparedStatement.setBoolean(5,(entity.getIsBlocked()));
    		preparedStatement.setBigDecimal(6,(entity.getAccount()));
    		preparedStatement.setInt(7,entity.getId());
    		
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		throw new SQLException(e);
		}
	}

	@Override
	public void delete(int id) throws SQLException{
		String sql= "Delete from users where users.id = ?";
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
}
