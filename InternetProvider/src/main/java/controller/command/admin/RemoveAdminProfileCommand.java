package controller.command.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class RemoveAdminProfileCommand  implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
	        UserDao dao = factory.createUserDao();
	        User currentUser = (User) request.getSession().getAttribute("user");
	        dao.delete(currentUser.getId());
	        return "/admin/logout";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
	}
	}
}
