package controller.command.admin;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.dao.TarifDao;
import model.entity.Service;
import model.entity.Tarif;

public class CreateTariffCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		try {
			DaoFactory factory = DaoFactory.getInstance();
			ServiceDao serviceDao = factory.createServiceDao();
			List<Service> serviceList = serviceDao.findAll();
	        int serviceId;
	        if(request.getParameter("serviceId")== null) {
	        	serviceId = serviceList.get(0).getId();
	        }else {
	        	serviceId=Integer.valueOf(request.getParameter("serviceId"));
	        }
	        Service service = serviceDao.findById(serviceId);
	        request.setAttribute("serviceAttr", service);
	        request.setAttribute("serviceList", serviceList);
			BigDecimal price;
			try {
				if(request.getParameter("price")==null) {
					price = BigDecimal.valueOf(0);
					request.setAttribute("price",0);
				}else {
					price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
				}
			}catch (NumberFormatException e) {
				request.setAttribute("message", "Wrong number format");
	            return "/admin/createTarif.jsp";
			}
			String name = request.getParameter("name");
	        String description = request.getParameter("description");
	        
	        if(name == null || name.equals("") || description == null || description.equals("") ||
	        							price == null || price.equals("")){
	        	request.setAttribute("message", "You have empty fields.");
	            return "/admin/createTarif.jsp";
	        }
	        if(price.doubleValue()<0 || price.doubleValue()>10000) {
	        	request.setAttribute("message", "Max price = 10000, min = 0");
	            return "/admin/createTarif.jsp";
	        }
	        
	        if(name.length()<4 || name.length()>12) {
	        	request.setAttribute("message", "Minimum length of name = 4.");									
	        	return "/admin/createTarif.jsp";
	        }
	        
	        TarifDao dao = factory.createTarifDao();
	        List<Tarif> list = dao.findAll();
	        for(Tarif tarif : list) {
	        	if(name.equals(tarif.getName())) {
	        		request.setAttribute("message", "This tarif name already exist.");
	        		return "/admin/createTarif.jsp";
	        	}
	        }
	        Tarif tarif = new Tarif(name, description, price);
	        tarif.setService(service);
	        dao.create(tarif);
	        return "/admin/adminMain";
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}
	

}
