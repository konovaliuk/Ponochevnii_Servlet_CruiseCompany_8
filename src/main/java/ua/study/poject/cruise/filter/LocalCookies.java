package ua.study.poject.cruise.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 *
 * The LocalCookies class is a filter that reads the locale from the user's cookies
 * and sets the locale in the current session to the value that is written in cookies.
 * If cookies do not have this setting, then the default locale is set.
 */
public class LocalCookies implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * The doFilter method reads the locale from the user's cookies and sets the locale in the current session to the value
     * that is written in cookies. If cookies do not have this setting, then the default locale is set.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
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
