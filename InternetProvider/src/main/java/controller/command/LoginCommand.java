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
            return "/login.jsp";
        }        
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
        List<User> userList = dao.findAll();
        for(User user : userList) {
        	if(login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                if (user.getRole().equals(User.ROLE.ADMIN)){
                    return "redirect:/admin/adminbasis.jsp";
                } else if(user.getRole().equals(User.ROLE.USER)) {
                    return "redirect:/user/userbasis.jsp";
                } 
        	}
        }
        return "/login.jsp";
    }

}