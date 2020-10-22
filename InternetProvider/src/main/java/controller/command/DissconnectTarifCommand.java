package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class DissconnectTarifCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
		UserDao daoUser = factory.createUserDao();
		User user = (User) request.getSession().getAttribute("user");
		int tarifId = Integer.valueOf(request.getParameter("tarifId"));
		daoUser.removeFromUsersTarifs(user.getId(), tarifId);
		return "/user/userProfile";
	}

}
