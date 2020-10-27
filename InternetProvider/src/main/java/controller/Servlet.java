package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.ClassExceptionCommand;
import controller.command.Command;
import controller.command.ExceptionCommand;
import controller.command.LogOutCommand;
import controller.command.LoginCommand;
import controller.command.MainPageCommand;
import controller.command.NotFoundExceptionCommand;
import controller.command.RegistrationCommand;
import controller.command.SQLExceptionCommand;
import controller.command.admin.AdminMainCommand;
import controller.command.admin.AdminProfileCommand;
import controller.command.admin.ConfirmUserCommand;
import controller.command.admin.CreateServiceCommand;
import controller.command.admin.CreateTariffCommand;
import controller.command.admin.CreateUserCommand;
import controller.command.admin.DeleteServiceCommand;
import controller.command.admin.DeleteTariffCommand;
import controller.command.admin.DeleteUserCommand;
import controller.command.admin.EditAdminCommand;
import controller.command.admin.EditServiceCommand;
import controller.command.admin.EditTarifCommand;
import controller.command.admin.GetAdminsCommand;
import controller.command.admin.GetServicesCommand;
import controller.command.admin.GetUsersCommand;
import controller.command.admin.RemoveAdminProfileCommand;
import controller.command.user.ConnectTarifCommand;
import controller.command.user.DissconnectTarifCommand;
import controller.command.user.EditAccountCommand;
import controller.command.user.EditUserCommand;
import controller.command.user.RemoveUserProfileCommand;
import controller.command.user.UserMainCommand;
import controller.command.user.UserProfileCommand;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<String, Command>();

    public void init(){
    	
    	commands.put("", new MainPageCommand());
    	commands.put("main" , new MainPageCommand());
    	commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("notFoundException" , new NotFoundExceptionCommand());
        commands.put("sqlException", new SQLExceptionCommand());
        commands.put("classException", new ClassExceptionCommand());
        commands.put("exception", new ExceptionCommand());
        
        commands.put("admin/" , new AdminMainCommand());
        commands.put("admin/adminMain" , new AdminMainCommand());
        commands.put("admin/users" , new GetUsersCommand());
        commands.put("admin/admins" , new GetAdminsCommand());
        commands.put("admin/confirmUser" , new ConfirmUserCommand());
        commands.put("admin/deleteUser" , new DeleteUserCommand());
        commands.put("admin/createUser" , new CreateUserCommand());
        commands.put("admin/adminProfile" , new AdminProfileCommand());
        commands.put("admin/editAdmin" , new EditAdminCommand());
        commands.put("admin/removeAdminProfile" , new RemoveAdminProfileCommand());
        commands.put("admin/createTarif" , new CreateTariffCommand());
        commands.put("admin/deleteTarif" , new DeleteTariffCommand());
        commands.put("admin/services" , new GetServicesCommand());
        commands.put("admin/createService" , new CreateServiceCommand());
        commands.put("admin/deleteService" , new DeleteServiceCommand());
        commands.put("admin/editService" , new EditServiceCommand());
        commands.put("admin/editTarif" , new EditTarifCommand());
        commands.put("admin/logout", new LogOutCommand());
        commands.put("admin/notFoundException", new NotFoundExceptionCommand());
        commands.put("admin/sqlException", new SQLExceptionCommand());
        commands.put("admin/classException", new ClassExceptionCommand());
        commands.put("admin/exception", new ExceptionCommand());
        
        commands.put("user/" , new UserMainCommand());
        commands.put("user/userMain" , new UserMainCommand());
        commands.put("user/userProfile" , new UserProfileCommand());
        commands.put("user/editUser" , new EditUserCommand());
        commands.put("user/removeUserProfile" , new RemoveUserProfileCommand());
        commands.put("user/connectTarif" , new ConnectTarifCommand());
        commands.put("user/dissconnectTarif" , new DissconnectTarifCommand());
        commands.put("user/editAccount" , new EditAccountCommand());
        commands.put("user/logout", new LogOutCommand());
        commands.put("user/notFoundException", new NotFoundExceptionCommand());
        commands.put("user/sqlException", new SQLExceptionCommand());
        commands.put("user/classException", new ClassExceptionCommand());
        commands.put("user/exception", new ExceptionCommand());
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
     	processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    		
    	processRequest(request, response);
		
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
	    	String path = request.getRequestURI();
	        path = path.replaceAll("/provider/" , "");
	        Command command = commands.get(path);
	        String page = command.execute(request);
	        if(page.contains("redirect:")){
	            response.sendRedirect(page.replace("redirect:", "/provider"));
	        }else {
	            request.getRequestDispatcher(page).forward(request, response);
	        }
        }catch (SQLException e) {
            request.getRequestDispatcher("sqlException").forward(request, response);
        }catch (ClassNotFoundException e) {
        	request.getRequestDispatcher("classException").forward(request, response);
		}catch (RuntimeException e) {
        	request.getRequestDispatcher("notFoundException").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("exception").forward(request, response);
		}
    }
}