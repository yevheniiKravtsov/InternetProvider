package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class RegistrationCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if(login == null || login.equals("") || password == null || password.equals("") ||
        							confirmPassword == null || confirmPassword.equals("") ){
        	request.setAttribute("message", "You have empty fields.");
            return "/registration.jsp";
        }
        if(!(password.equals(confirmPassword))){
        	request.setAttribute("message", "You have to confirm your password correctly.");
        	return "/registration.jsp";
        }
        if(login.length()<4 || login.length()>12 || password.length()<4 || password.length()>12) {
        	request.setAttribute("message", "Minimum length of login and password = 4.\n"
        									+ "Maximum length of login and password = 12");
        	return "/registration.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
        List<User> userList = dao.findAll();
        for(User user : userList) {
        	if(login.equals(user.getLogin())) {
        		request.setAttribute("message", "This login already exist.");
        		return "/registration.jsp";
        	}
        }
        User user = new User(login,password,User.ROLE.USER);
        dao.create(user);
		return "index.jsp";
	}

}
