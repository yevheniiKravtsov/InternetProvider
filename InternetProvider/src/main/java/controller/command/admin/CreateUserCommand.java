package controller.command.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class CreateUserCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String login = request.getParameter("login");
	        String password = request.getParameter("password");
	        String confirmPassword = request.getParameter("confirmPassword");
	        String role = request.getParameter("role");
	        if(login == null || login.equals("") || password == null || password.equals("") ||
	        							confirmPassword == null || confirmPassword.equals("") || role == null ){
	        	request.setAttribute("message", "You have empty fields.");
	            return "/admin/createUser.jsp";
	        }
	        if(!(password.equals(confirmPassword))){
	        	request.setAttribute("message", "You have to confirm your password correctly.");
	        	return "/admin/createUser.jsp";
	        }
	        if(login.length()<4 || login.length()>12 || password.length()<4 || password.length()>12) {
	        	request.setAttribute("message", "Minimum length of login and password = 4.\n"
	        									+ "Maximum length of login and password = 12");
	        	return "/admin/createUser.jsp";
	        }
	        DaoFactory factory = DaoFactory.getInstance();
	        UserDao dao = factory.createUserDao();
	        List<User> list = dao.findAll();
	        for(User user : list) {
	        	if(login.equals(user.getLogin())) {
	        		request.setAttribute("message", "This login already exist.");
	        		return "/admin/createUser.jsp";
	        	}
	        }
	        User user = new User(login,password,User.ROLE.valueOf(role),true);
	        dao.create(user);
	        List<User> userList = dao.findAllByRole(User.ROLE.USER);
	        request.setAttribute("userList", userList);
	        return "/admin/getUsers.jsp";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}

	}

}
