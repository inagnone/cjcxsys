package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Logger;
import org.slf4j.MDC;





public class LogFilter implements Filter {

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
		 HttpSession session= req.getSession(); 
		 if(session != null){
			 String username =  (String) session.getAttribute("username");
			 if(username != null && !username.equals("")){
				 ThreadContext.put("username", username);
				 ThreadContext.put("userip", req.getLocalAddr());
				 chain.doFilter(req, resp);
			 }else{
				 req.getRequestDispatcher("login.jsp").forward(req, resp);
				 return ;
			 }
		 }else{
			 req.getRequestDispatcher("login.jsp").forward(req, resp);
			 return ;
		 }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
