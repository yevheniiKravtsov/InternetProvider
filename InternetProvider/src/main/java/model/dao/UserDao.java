package model.dao;

import java.util.List;

import model.entity.User;
import model.entity.User.ROLE;

public interface UserDao extends GenericDao<User> {

	List<User> findAllByRole(ROLE user);

	List<User> findAllByConfirmation(boolean b);
}
