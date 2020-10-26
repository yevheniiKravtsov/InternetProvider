package controller.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.TarifDao;
import model.dao.UserDao;
import model.entity.Tarif;
import model.entity.User;

public class ConnectTarifCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
		UserDao daoUser = factory.createUserDao();
		TarifDao daoTarif = factory.createTarifDao();
		User user = (User) request.getSession().getAttribute("user");
		int tarifId = Integer.valueOf(request.getParameter("tarifId"));
		Tarif tarif = daoTarif.findById(tarifId);
		if(tarif.getPrice().compareTo(user.getAccount())>0) {
			request.setAttribute("message", "Update your account");
            return "/user/userMain";
		}else {
			user.setAccount(BigDecimal.valueOf(user.getAccount().doubleValue()-tarif.getPrice().doubleValue()));
			daoUser.insertIntoUsersTarifs(user.getId(), tarifId);
			daoUser.update(user);
		}	
		return "/user/userMain";
	}
}
