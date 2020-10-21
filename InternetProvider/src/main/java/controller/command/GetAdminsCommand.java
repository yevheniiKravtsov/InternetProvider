package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class GetAdminsCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
        List<User> userList = dao.findAllByRole(User.ROLE.ADMIN);
        request.setAttribute("userList", userList);
		return "/admin/getUsers.jsp";
	}

}
