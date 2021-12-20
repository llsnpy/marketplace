package by.mironenko.marketplace.controller.filter;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.controller.command.impl.LanguagesCommandImpl;
import by.mironenko.marketplace.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Pavel Mironenko
 * This filter should restrict user actions based on their roler.
 * Not used.
 */
public class SecurityFilter implements Filter {
    private static Logger log = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Command command = (Command) httpRequest.getAttribute("command");
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
                String errorMessage = (String) session.getAttribute("SecurityFilterMessage");
                if (errorMessage != null) {
                    httpRequest.setAttribute("message", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }
            boolean canExecute = false;
            if (user != null) {
                userName = "\"" + user.getLogin() + "\" user";
                canExecute = true;
            }
            if (canExecute) {
                filterChain.doFilter(request, response);
            } else {
                log.info("Trying of %s access to forbidden resource \"%s\"");
                if (session != null && command.getClass() != LanguagesCommandImpl.class) {
                    session.setAttribute("SecurityFilterMessage", "Access is denied");
                }
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
            }
        } else {
            log.error("Impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB_INF/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
