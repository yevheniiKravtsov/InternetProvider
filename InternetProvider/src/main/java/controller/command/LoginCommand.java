package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;


public class LoginCommand implements Command{

	@Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login == null || login.equals("") || password == null || password.equals("")  ){
        	request.setAttribute("message", "You have empty fields.");
            return "/login.jsp";
        }        
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
        List<User> userList = dao.findAll();
        for(User user : userList) {
        	if(login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                if (user.getRole().equals(User.ROLE.ADMIN)){
                	CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
                	CommandUtility.setUserAsAttribute(request, user);
                    return "redirect:/admin/adminMain";
                } else if(user.getRole().equals(User.ROLE.USER)) {
                	if(user.getIsConfirmed()) {
                		CommandUtility.setUserRole(request, User.ROLE.USER, login);
                		CommandUtility.setUserAsAttribute(request, user);
                		return "redirect:/user/userMain";
                	}
                	else {
                		request.setAttribute("message", "Your account is not confirmed by admin. Try again later");
                		return "/login.jsp";
                	}
                }   
        	}
        }
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, login);
        request.setAttribute("message", "Wrong login or password.");
        return "/login.jsp";
    }

}
