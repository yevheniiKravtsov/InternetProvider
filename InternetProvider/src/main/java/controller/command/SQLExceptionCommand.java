package controller.command;

import javax.servlet.http.HttpServletRequest;

public class SQLExceptionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		request.setAttribute("message", "Data Base Exception");
		return "exception.jsp";
	
	}

}
