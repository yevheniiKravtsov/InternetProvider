package controller.command;

import javax.servlet.http.HttpServletRequest;

public class NotFoundExceptionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		request.setAttribute("message", "HTTP Status 404 - Not Found");
		return "exception.jsp";
	
	}

}
