package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.entity.User;
import model.entity.User.ROLE;

public interface UserDao extends GenericDao<User> {

	List<User> findAllByRole(ROLE user) throws Exception;

	List<User> findAllByConfirmation(boolean b) throws Exception;

	User findTarifsForUser(User user) throws Exception;

	void insertIntoUsersTarifs(int id, int tarifId) throws Exception;

	void removeFromUsersTarifs(int id, int tarifId) throws Exception;

}
