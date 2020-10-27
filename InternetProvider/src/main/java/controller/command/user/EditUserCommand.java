package controller.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class EditUserCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String newLogin = request.getParameter("newLogin");
	        String oldPassword = request.getParameter("oldPassword");
	        String newPassword = request.getParameter("newPassword");
	        User currentUser = (User) request.getSession().getAttribute("user");
	        if(newLogin == null || newLogin.equals("") || oldPassword == null || oldPassword.equals("") ||
	        							newPassword == null || newPassword.equals("") ){
	        	request.setAttribute("message", "You have empty fields.");
	            return "/user/editUser.jsp";
	        }
	        if(!(currentUser.getPassword().equals(oldPassword))){
	        	request.setAttribute("message", "Wrong odl password.");
	        	return "/user/editUser.jsp";
	        }
	        if(newLogin.length()<4 || newLogin.length()>12 || newPassword.length()<4 || newPassword.length()>12) {
	        	request.setAttribute("message", "Minimum length of login and password = 4.\n"
	        									+ "Maximum length of login and password = 12");
	        	return "/user/editUser.jsp";
	        }
	        DaoFactory factory = DaoFactory.getInstance();
	        UserDao dao = factory.createUserDao();
	        if(!(currentUser.getLogin().equals(newLogin))) {      
		        List<User> userList = dao.findAll();
		        for(User user : userList) {
		        	if(newLogin.equals(user.getLogin())) {
		        		request.setAttribute("message", "This login already exist.");
		        		return "/user/editUser.jsp";
		        	}
		        }
	        }
	        currentUser.setLogin(newLogin);
	        currentUser.setPassword(newPassword);
	        dao.update(currentUser);
			return "/user/userProfile.jsp";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
	}
	}

}
