package ua.study.poject.cruise.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * The Form EncodingFilter class is used to set the appropriate encoding in the request
 */
public class EncodingFilter implements Filter {
    private static final String FILTERBLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String encoding;


    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }

    /**
     * Method doFilter sets the encoding to UTF-8 if it is not set
     * and transfers control to the next filter or servlet
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.startsWith(FILTERBLE_CONTENT_TYPE))
            servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
