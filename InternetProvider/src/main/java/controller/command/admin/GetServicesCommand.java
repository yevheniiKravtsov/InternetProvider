package controller.command.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.entity.Service;

public class GetServicesCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
	        ServiceDao dao = factory.createServiceDao();
	        List<Service> serviceList = dao.findAll();
	        request.setAttribute("serviceList", serviceList);
			return "/admin/getServices.jsp";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

}
