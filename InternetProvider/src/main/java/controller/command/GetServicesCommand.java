package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.entity.Service;

public class GetServicesCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        ServiceDao dao = factory.createServiceDao();
        List<Service> serviceList = dao.findAll();
        request.setAttribute("serviceList", serviceList);
		return "/admin/getServices.jsp";
	}

}
