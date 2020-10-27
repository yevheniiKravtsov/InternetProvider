package controller.command.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class RemoveUserProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
	        UserDao dao = factory.createUserDao();
	        User currentUser = (User) request.getSession().getAttribute("user");
	        dao.delete(currentUser.getId());
	        return "/user/logout";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

}
