package controller.command;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.dao.TarifDao;
import model.entity.Service;
import model.entity.Tarif;

public class EditTarifCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        TarifDao dao = factory.createTarifDao();
        ServiceDao serviceDao = factory.createServiceDao();
        int tarifId = Integer.valueOf(request.getParameter("tarifId"));
        List<Service> serviceList = serviceDao.findAll();
        Tarif currentTarif = dao.findById(tarifId);
        String newName = request.getParameter("newName");
		String newDescription = request.getParameter("newDescription");
		int serviceId;
		if(request.getParameter("serviceId")==null) {
			serviceId = currentTarif.getService().getId();
		}else {
			serviceId = Integer.valueOf(request.getParameter("serviceId"));
		}
		BigDecimal newPrice;
		try {
			if(request.getParameter("newPrice")==null) {
				newPrice = currentTarif.getPrice();
			}else {
				newPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter("newPrice")));
			}
			
		} catch (NumberFormatException e){
			newPrice = currentTarif.getPrice();
			request.setAttribute("message", "Wrong number format");
			request.setAttribute("tarif", currentTarif);
	        request.setAttribute("currentService", currentTarif.getService());
	        request.setAttribute("serviceList", serviceList);
            return "/admin/editTarif.jsp";
		}
		request.setAttribute("tarif", currentTarif);
        request.setAttribute("currentService", currentTarif.getService());
        request.setAttribute("serviceList", serviceList);
		
		if(newName == null || newName.equals("") || newDescription == null || newDescription.equals("")){
        	request.setAttribute("message", "You have empty fields.");
            return "/admin/editTarif.jsp";
        }
        
		
		if(newPrice.doubleValue()<0 || newPrice.doubleValue()>10000) {
        	request.setAttribute("message", "Max price = 10000, min = 0");
            return "/admin/editTarif.jsp";
        }
		
		
  
        if(newName.length()<4 || newName.length()>12) {
        	request.setAttribute("message", "Minimum length = 4.\n"
        									+ "Maximum length = 12");
        	return "/admin/editTarif.jsp";
        }
        
        List<Tarif> list = dao.findAll();
        if(!(newName.equals(currentTarif.getName()))){
	        for(Tarif tarif : list) {
	        	if(newName.equals(tarif.getName())) {
	        		request.setAttribute("message", "This tarif name already exist.");
	        		return "/admin/editTarif.jsp";
	        	}
	        }
        }
		
        Service newService = serviceDao.findById(serviceId);
        currentTarif.setName(newName);
        currentTarif.setDescription(newDescription);
        currentTarif.setPrice(newPrice);
        currentTarif.setService(newService);
        dao.update(currentTarif);
		return "/admin/adminMain";
	}

}
