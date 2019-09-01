package ua.epam.poject.cruise.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyFilterLogin implements javax.servlet.Filter {
    static int count = 0;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init MyFilterLogin");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("<<<<<<<<<<<<<   doFilter вызван " + count++ + " раз!");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if(session == null) {
            System.out.println("MyFilterLogin: session == null");
        }
        System.out.println("из фильтра MyFilterLogin: session = " + request.getSession(false));

        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyFilterLogin после doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy MyFilterLogin");
    }
}
