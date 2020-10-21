package model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dao.UserDao;
import model.entity.User;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

	@Override
	public void create(User entity) {
		String sql= "Insert into users (login, password, role, confirmation) Values (?,?,?,?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		preparedStatement.setString(1,entity.getLogin());
    		preparedStatement.setString(2,entity.getPassword());
    		preparedStatement.setString(3,entity.getRole().toString());
    		preparedStatement.setBoolean(4,entity.getIsConfirmed());
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
			e.printStackTrace();
		}		
	}

	@Override//Optinal todo!
	public User findById(int id) {
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
				user = new User(login, password, role,isConfirmed);
				user.setId(userId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return user;
	}
	
	public List<User> findAllByRole(User.ROLE role) {
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
				User user = new User(login, password, userRole, isConfirmed);
				user.setId(id);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}
	
	public List<User> findAllByConfirmation(boolean confirmation) {
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
				User user = new User(login, password, userRole, isConfirmed);
				user.setId(id);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public List<User> findAll() {
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
				User user = new User(login, password, role, isConfirmed);
				user.setId(id);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public void update(User entity) {
		String sql= "Update users Set login=?, password=?, role=?, confirmation=? Where users.id=?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setString(1,(entity.getLogin()));
    		preparedStatement.setString(2,(entity.getPassword()));
    		preparedStatement.setString(3,(entity.getRole().toString()));
    		preparedStatement.setBoolean(4,(entity.getIsConfirmed()));
    		preparedStatement.setInt(5,entity.getId());
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql= "Delete from users where users.id = ?";
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
