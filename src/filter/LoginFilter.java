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
		
		
		ServletContext context = req.getSession().getServletContext().getContext("/Certificate");
		if(context != null){
			String username  = (String) context.getAttribute("username");
			if(username != null && !username.equals("")){
				req.getSession().setAttribute("username", username);
				chain.doFilter(request, response);	
			}else{
				req.getSession().removeAttribute("username");
				resp.sendRedirect("index.jsp");
				return ;
			}
		}else{
			req.getSession().removeAttribute("username");
			resp.sendRedirect("index.jsp");
			return ;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
