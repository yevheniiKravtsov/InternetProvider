package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		request.setAttribute("message","Error");
		return "exception.jsp";
	
	}

}
