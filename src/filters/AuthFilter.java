package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
     
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
 
            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            if ( reqURI.indexOf("/login.xhtml") >= 0 || reqURI.indexOf("/registro.xhtml") >= 0 || reqURI.indexOf("/estadisticas.xhtml") >= 0 || (ses != null && ses.getAttribute("email") != null)
                                       || reqURI.contains("javax.faces.resource"))
            	
            {
            	//1st auth phase passed!
            	if (reqURI.indexOf("/login.xhtml") >= 0 || reqURI.indexOf("/registro.xhtml") >= 0 || reqURI.indexOf("/estadisticas.xhtml") >= 0 || (ses != null && autorizar((String)ses.getAttribute("role"),reqURI) ) )
            		//2nd phase
            		chain.doFilter(request, response);
            	else{
            		 res.sendRedirect(req.getContextPath() + "/user/home.xhtml");
            	}
            }
                   
            else   // user didn't log in but asking for a page that is not allowed so take user to login page
                   res.sendRedirect(req.getContextPath() + "/login.xhtml");  // Anonymous user. Redirect to login page
      }
     catch(Throwable t) {
         System.out.println( t.getMessage());
     }
    } //end doFilter
 
    
    private boolean autorizar(String role,String reqURI){
    	if(role.compareTo("admin")==0) //Es admin hace lo que quiere
    		return true;
    	else{ //Es usuario, no puede acceder a los files de admin
    		if(reqURI.indexOf("/admin/") >= 0)
    			return false;
    		else
    			return true;
    	}
    		
    }
    
    
    @Override
    public void destroy() {
         
    }
}