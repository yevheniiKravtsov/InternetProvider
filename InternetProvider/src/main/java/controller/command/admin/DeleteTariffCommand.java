package controller.command.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.TarifDao;

public class DeleteTariffCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
	        TarifDao dao = factory.createTarifDao();
	        int id = Integer.valueOf(request.getParameter("tarifId"));
	        dao.delete(id);
	        return "/admin/adminMain";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

}
