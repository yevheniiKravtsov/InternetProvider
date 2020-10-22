package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.User;

public class LogOutCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().invalidate();
		request.getServletContext().removeAttribute("login");
		return "redirect:/main";
	}

}
