package controller.command.admin;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class AdminProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return "/admin/adminProfile.jsp";
	}
}
