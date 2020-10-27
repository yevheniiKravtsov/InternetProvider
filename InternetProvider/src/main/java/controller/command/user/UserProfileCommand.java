package controller.command.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class UserProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
			UserDao daoUser = factory.createUserDao();
			User user = (User) request.getSession().getAttribute("user");
	        user = daoUser.findTarifsForUser(user);
	        request.getSession().setAttribute("user",user);
			return "/user/userProfile.jsp";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

}
