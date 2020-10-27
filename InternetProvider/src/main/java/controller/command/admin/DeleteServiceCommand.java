package controller.command.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.ServiceDao;

public class DeleteServiceCommand implements Command{

		@Override
		public String execute(HttpServletRequest request) throws Exception {
			try {
				DaoFactory factory = DaoFactory.getInstance();
		        ServiceDao dao = factory.createServiceDao();
		        int id = Integer.valueOf(request.getParameter("serviceId"));
		        dao.delete(id);
		        return "/admin/services";
			} catch (SQLException e) {
				throw new SQLException();
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException();
			}
		}

}
