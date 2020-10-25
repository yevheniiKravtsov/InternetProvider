package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.ServiceDao;

public class DeleteServiceCommand implements Command{

		@Override
		public String execute(HttpServletRequest request) {
			DaoFactory factory = DaoFactory.getInstance();
	        ServiceDao dao = factory.createServiceDao();
	        int id = Integer.valueOf(request.getParameter("serviceId"));
	        dao.delete(id);
	        return "/admin/services";
		}

}
