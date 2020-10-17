package controller.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.entity.User;

class CommandUtility {

	static void setUserRole(HttpServletRequest request, User.ROLE role, String login) {
		HttpSession session = request.getSession();
		ServletContext context =  request.getServletContext();
		context.setAttribute("login", login);
		session.setAttribute("role", role);
	}

}
