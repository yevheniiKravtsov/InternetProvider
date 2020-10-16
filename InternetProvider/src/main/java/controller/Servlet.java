package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;
import controller.command.ExceptionCommand;
import controller.command.LogOutCommand;
import controller.command.LoginCommand;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<String, Command>();

    public void init(){
    	commands.put("index", new Command(){
			@Override
			public String execute(HttpServletRequest request) {
				return "/index.jsp";
			}});
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("exception" , new ExceptionCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll("/InternetProvider/" , "");
        System.out.println(path);
        Command command = commands.get(path);
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/InternetProvider"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}