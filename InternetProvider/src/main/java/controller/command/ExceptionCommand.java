package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		throw new RuntimeException("Runtime exception");
	}

}
