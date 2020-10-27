package controller.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.dao.TarifDao;
import model.dao.UserDao;
import model.entity.Service;
import model.entity.Tarif;
import model.entity.User;

public class UserMainCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
	        ServiceDao dao = factory.createServiceDao();
	        TarifDao daoTarif = factory.createTarifDao();
	        UserDao daoUser = factory.createUserDao();
	        User user = (User) request.getSession().getAttribute("user");
	        user = daoUser.findTarifsForUser(user);
	        request.getSession().setAttribute("user",user);
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
			return "/user/userbasis.jsp";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

}
