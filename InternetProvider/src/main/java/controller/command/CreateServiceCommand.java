package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.entity.Service;

public class CreateServiceCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		String name = request.getParameter("name");
        if(name == null || name.equals("")){
        	request.setAttribute("message", "You have empty fields.");
            return "/admin/createService.jsp";
        }
        if(name.length()<4 || name.length()>12) {
        	request.setAttribute("message", "Minimum length of login and password = 4.\n"
        									+ "Maximum length of login and password = 12");
        	return "/admin/createService.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        ServiceDao dao = factory.createServiceDao();
        List<Service> list = dao.findAll();
        for(Service service : list) {
        	if(name.equals(service.getName())) {
        		request.setAttribute("message", "This name already exist.");
        		return "/admin/createService.jsp";
        	}
        }
        Service service = new Service(name);
        dao.create(service);
        return "/admin/services";
	}

}
