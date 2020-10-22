package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.dao.TarifDao;
import model.entity.Service;
import model.entity.Tarif;

public class AdminMainCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		DaoFactory factory = DaoFactory.getInstance();
        ServiceDao dao = factory.createServiceDao();
        TarifDao daoTarif = factory.createTarifDao();
        List<Service> serviceList = dao.findAll();
        int serviceId;
        String sortBy;
        if(request.getParameter("serviceId")== null) {
        	serviceId = serviceList.get(0).getId();
        }else {
        	serviceId=Integer.valueOf(request.getParameter("serviceId"));
        }
        if(request.getParameter("sort")== null) {
        	sortBy = "ByPrice";
        }else {
        	sortBy=request.getParameter("sort");
        }
        List<Tarif> tarifList;
        switch(sortBy) {
        	case "ByName(a-z)":
        		tarifList = daoTarif.findSortedByNameASCWithServiceId(serviceId);
        		break;
        	case "ByName(z-a)":
        		tarifList = daoTarif.findSortedByNameDESCWithServiceId(serviceId);
        		break;
        	case "ByPrice":
        		tarifList = daoTarif.findSortedByPriceWithServiceId(serviceId);
        		break;
        	default:
        		tarifList = daoTarif.findSortedByPriceWithServiceId(serviceId);
        		break;
        }
        
        Service service = dao.findById(serviceId);
        request.setAttribute("sort", sortBy);
        request.setAttribute("serviceAttr", service);
        request.setAttribute("tarifList", tarifList);
		request.setAttribute("serviceList", serviceList);
		return "/admin/adminbasis.jsp";
	}

}
