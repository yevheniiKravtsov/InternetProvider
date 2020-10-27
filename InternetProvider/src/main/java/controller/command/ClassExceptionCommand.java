package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ClassExceptionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		request.setAttribute("message", "Problem with data base driver connector");
		return "exception.jsp";
	
	}

}
