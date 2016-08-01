package filter;

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




public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
//		<%HttpSession session0 = request.getSession(false); %>
//	  	<%if(session0 != null){ %>
//		  	<%ServletContext context = session0.getServletContext().getContext("/Certificate"); %>
//		  	<%if(context != null){ %>
//		  		<%HttpSession session2  = (HttpSession)context.getAttribute("session");  %>
//		  		<%if(session2 != null ){ %>
//		  			<%session0.setAttribute("user", session2.getAttribute("user")); %>
//		  		<%}else{ %>
//		  			<%session0.removeAttribute("user"); %>
//		  		<%} %>
//	  		<%}else{ %>
//	  			<%session0.removeAttribute("user"); %>
//	  		<%} %>
//	  	<%} %>
		
		
		ServletContext context = req.getSession().getServletContext().getContext("/Certificate");
		if(context != null){
			HttpSession session  = (HttpSession)context.getAttribute("session");
			if(session != null){
				req.getSession().setAttribute("user", session.getAttribute("user"));
			}else{
				req.getSession().removeAttribute("user");
			}
		}else{
			req.getSession().removeAttribute("user");
		}
		chain.doFilter(request, response);	
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
