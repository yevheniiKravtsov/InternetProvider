package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.TarifDao;

public class DeleteTariffCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        TarifDao dao = factory.createTarifDao();
        int id = Integer.valueOf(request.getParameter("tarifId"));
        dao.delete(id);
        return "/admin/adminMain";
	}

}
