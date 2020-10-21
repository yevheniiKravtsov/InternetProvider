package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class RemoveUserProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
        User currentUser = (User) request.getSession().getAttribute("user");
        dao.delete(currentUser.getId());
        return "/user/logout";
	}

}
