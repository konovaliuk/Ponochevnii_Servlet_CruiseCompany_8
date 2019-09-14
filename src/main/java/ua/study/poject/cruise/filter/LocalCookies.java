package ua.study.poject.cruise.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocalCookies implements Filter {
    private static final String FILTERBLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String encoding;


    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Если нет сессии, то смотрим язык локали в Cookie. Если там нет, то устанавливаем по умолчанию
        String language = null;
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("language")) {
                language = cookie.getValue();
            }
        }

        String paramLanguage = servletRequest.getParameter("language");
        if(paramLanguage != null && !paramLanguage.equals(""))
            language = paramLanguage;

        if (language == null || language.equals(""))
            language = servletRequest.getLocale().getLanguage();

        if (language != null && !language.equals(""))
            servletRequest.setAttribute("language", language);

        Locale.setDefault(new Locale(language));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
