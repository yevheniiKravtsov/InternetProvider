package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class EditAdminCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		String newLogin = request.getParameter("newLogin");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        User currentUser = (User) request.getSession().getAttribute("user");
        if(newLogin == null || newLogin.equals("") || oldPassword == null || oldPassword.equals("") ||
        							newPassword == null || newPassword.equals("") ){
        	request.setAttribute("message", "You have empty fields.");
            return "/admin/editAdmin.jsp";
        }
        if(!(currentUser.getPassword().equals(oldPassword))){
        	request.setAttribute("message", "Wrong odl password.");
        	return "/admin/editAdmin.jsp";
        }
        if(newLogin.length()<4 || newLogin.length()>12 || newPassword.length()<4 || newPassword.length()>12) {
        	request.setAttribute("message", "Minimum length of login and password = 4.\n"
        									+ "Maximum length of login and password = 12");
        	return "/admin/editAdmin.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
        if(!(currentUser.getLogin().equals(newLogin))) {      
	        List<User> userList = dao.findAll();
	        for(User user : userList) {
	        	if(newLogin.equals(user.getLogin())) {
	        		request.setAttribute("message", "This login already exist.");
	        		return "/admin/editAdmin.jsp";
	        	}
	        }
        }
        currentUser.setLogin(newLogin);
        currentUser.setPassword(newPassword);
        dao.update(currentUser);
		return "/admin/adminProfile.jsp";
	}

}
