package controller.command;

import javax.servlet.http.HttpServletRequest;

public class UserProfileCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return "/user/userProfile.jsp";
	}

}
