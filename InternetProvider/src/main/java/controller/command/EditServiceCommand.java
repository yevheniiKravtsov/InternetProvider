package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.entity.Service;

public class EditServiceCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        ServiceDao dao = factory.createServiceDao();
		String newName = request.getParameter("newName");
        int serviceId = Integer.valueOf(request.getParameter("serviceId"));
        Service currentService = dao.findById(serviceId);
        request.setAttribute("service", currentService);
        
        if(newName == null || newName.equals("")){
        	request.setAttribute("message", "You have empty fields.");
            return "/admin/editService.jsp";
        }
        
        if(newName.length()<4 || newName.length()>12) {
        	request.setAttribute("message", "Minimum length = 4.\n"
        									+ "Maximum length = 12");
        	return "/admin/editService.jsp";
        }
        
        List<Service> serviceList = dao.findAll();
        if(!(newName.equals(currentService.getName()))){
	        for(Service service : serviceList) {
	        	if(newName.equals(service.getName())) {
	        		request.setAttribute("message", "This name already exist.");
	        		return "/admin/editService.jsp";
	        	}
	        }
        }
        currentService.setName(newName);
        dao.update(currentService);
		return "/admin/services";
	}

}
