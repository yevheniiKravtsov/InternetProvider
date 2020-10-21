package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.AdminProfileCommand;
import controller.command.Command;
import controller.command.ConfirmUserCommand;
import controller.command.CreateUserCommand;
import controller.command.DeleteUserCommand;
import controller.command.EditAdminCommand;
import controller.command.EditUserCommand;
import controller.command.ExceptionCommand;
import controller.command.GetAdminsCommand;
import controller.command.GetUsersCommand;
import controller.command.LogOutCommand;
import controller.command.LoginCommand;
import controller.command.RegistrationCommand;
import controller.command.RemoveAdminProfileCommand;
import controller.command.RemoveUserProfileCommand;
import controller.command.UserProfileCommand;

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
        commands.put("registration", new RegistrationCommand());
        commands.put("exception" , new ExceptionCommand());
        commands.put("users" , new GetUsersCommand());
        commands.put("admins" , new GetAdminsCommand());
        commands.put("confirmUser" , new ConfirmUserCommand());
        commands.put("deleteUser" , new DeleteUserCommand());
        commands.put("createUser" , new CreateUserCommand());
        commands.put("userProfile" , new UserProfileCommand());
        commands.put("editUser" , new EditUserCommand());
        commands.put("removeUserProfile" , new RemoveUserProfileCommand());
        commands.put("adminProfile" , new AdminProfileCommand());
        commands.put("editAdmin" , new EditAdminCommand());
        commands.put("removeAdminProfile" , new RemoveAdminProfileCommand());
        
        
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
        if(path.contains("/admin/")) {
        	path = path.replaceAll("/InternetProvider/admin/" , "");
        }else if(path.contains("/user/")) {
        	path = path.replaceAll("/InternetProvider/user/" , "");
        }else {
        	path = path.replaceAll("/InternetProvider/" , "");
        } 
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