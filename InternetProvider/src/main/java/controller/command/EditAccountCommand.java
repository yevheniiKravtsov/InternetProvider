package controller.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class EditAccountCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
	    UserDao dao = factory.createUserDao();
		User currentUser = (User) request.getSession().getAttribute("user");
		BigDecimal account;
		try {
			if(request.getParameter("account") == null) {
				account = currentUser.getAccount();
				request.setAttribute("account",account);
				return "/user/editAccount.jsp";
			}else {
				account = BigDecimal.valueOf(Double.valueOf(request.getParameter("account")));
			}
			
		} catch (NumberFormatException e){
			account = currentUser.getAccount();
			request.setAttribute("account",account);
			request.setAttribute("message", "Wrong number format");
            return "/user/editAccount.jsp";
		}
		if(account.doubleValue()< currentUser.getAccount().doubleValue()|| account.doubleValue()>10000) {
			account = currentUser.getAccount();
			request.setAttribute("account",account);
			request.setAttribute("message", "Max value = 10000, min = "+currentUser.getAccount().doubleValue());
            return "/user/editAccount.jsp";
        }
		currentUser.setAccount(account);
		dao.update(currentUser);		
		return "/user/userProfile";
	}

}
