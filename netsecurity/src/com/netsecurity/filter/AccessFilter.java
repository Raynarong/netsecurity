package com.netsecurity.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AccessFilter  implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {


		HttpServletRequest request = (HttpServletRequest)arg0; 
        HttpServletResponse response = (HttpServletResponse)arg1; 
        HttpSession session = request.getSession(); 
        System.out.println("URI:"+request.getRequestURI());
        System.out.println("URL:"+new String(request.getRequestURL()));
        String url=new String(request.getRequestURL());
        
       
       if(!url.contains("login")){
       if(session.getAttribute("user")== null)
       { 
    	   
            response.sendRedirect("/login.jsp"); 
            System.out.println("filting-----------------");
           return ; 
        }
       else
        filterChain.doFilter(arg0, arg1); 
       }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
