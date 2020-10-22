package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class UserProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
		UserDao daoUser = factory.createUserDao();
		User user = (User) request.getSession().getAttribute("user");
        user = daoUser.findTarifsForUser(user);
        request.getSession().setAttribute("user",user);
		return "/user/userProfile.jsp";
	}

}
