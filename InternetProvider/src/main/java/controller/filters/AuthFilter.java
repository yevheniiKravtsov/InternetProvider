package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.User;

public class AuthFilter implements Filter{

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try {
			final HttpServletRequest req = (HttpServletRequest) request;
			final HttpServletResponse res = (HttpServletResponse) response;
	        HttpSession session = req.getSession();
	        if(session.getAttribute("role")==null) {
	        	session.setAttribute("role", User.ROLE.UNKNOWN);
	        }
	        ServletContext context = request.getServletContext();
	        
	        if(req.getRequestURI().contains("/admin/")) {
	        	if(session.getAttribute("role").equals(User.ROLE.ADMIN)) {
	        		chain.doFilter(request,response);
	        	}else if(session.getAttribute("role").equals(User.ROLE.USER)) {
	        		res.sendRedirect(req.getContextPath() + "/user/userMain");
	        	}else {
	        		res.sendRedirect(req.getContextPath() + "/main");
	        	}
	        }else if(req.getRequestURI().contains("/user/")) {
	        	if(session.getAttribute("role").equals(User.ROLE.ADMIN)) {
	        		res.sendRedirect(req.getContextPath() + "/admin/adminMain");
	        	}else if(session.getAttribute("role").equals(User.ROLE.USER)) {
	        		chain.doFilter(request,response);
	        	}else {
	        		res.sendRedirect(req.getContextPath() + "/main");
	        	}
	        }else {
	        	if(session.getAttribute("role").equals(User.ROLE.ADMIN)) {
	        		res.sendRedirect(req.getContextPath() + "/admin/adminMain");
	        	}else if(session.getAttribute("role").equals(User.ROLE.USER)) {
	        		res.sendRedirect(req.getContextPath() + "/user/userMain");
	        	}else {
	        		chain.doFilter(request,response);
	        	}
	        }
		} catch (IOException e) { 
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	@Override
    public void destroy() {
		
    }

}
