package ua.epam.poject.cruise.filter;

import org.w3c.dom.ls.LSOutput;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyFilterTime implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Init MyFilterTime");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long timeStart = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("время обработки запроса = " + (System.currentTimeMillis() - timeStart) + " мс");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy MyFilterTime");
    }
}
