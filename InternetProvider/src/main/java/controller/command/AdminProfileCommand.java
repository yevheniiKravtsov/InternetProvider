package controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return "/admin/adminProfile.jsp";
	}
}
