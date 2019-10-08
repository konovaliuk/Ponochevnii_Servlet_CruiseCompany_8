package ua.study.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classes that implement interface Action are command handlers.
 * Each class processes one command that comes from the user.
 * This interface has a single execute method.
 */
public interface Action {

    /**
     * The execute method accepts all the parameters
     * necessary for work from request and performs the corresponding actions.
     *
     * @param request  All data that is necessary to execute the command is in request
     * @param response It can be used to send data to the user, for example Cookie
     * @return Returns the link to the page to be rendered
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
