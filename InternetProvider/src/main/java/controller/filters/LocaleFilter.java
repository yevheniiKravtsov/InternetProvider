package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LocaleFilter implements Filter{

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try {
			final HttpServletRequest req = (HttpServletRequest) request;
	       
			if(req.getSession().getAttribute("lang")==null) {
				req.getSession().setAttribute("lang", "en");
			}
	      	if (req.getParameter("localization") != null) {
	            req.getSession().setAttribute("lang", req.getParameter("localization"));
	        }
	      	
	        chain.doFilter(request, response);
	       
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
