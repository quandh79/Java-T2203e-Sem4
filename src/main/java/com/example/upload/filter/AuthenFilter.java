package com.example.upload.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = ("/upload-file"))
public class AuthenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession();
            String username = (String)session.getAttribute("username");
            String uri = request.getRequestURI();
            if (username == null){
                response.sendRedirect("login-servlet");

            }
            filterChain.doFilter(request,response);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
