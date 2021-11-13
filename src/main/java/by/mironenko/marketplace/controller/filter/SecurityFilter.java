package by.mironenko.marketplace.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            Action action = (Action) httpRequest.getAttribute("action");
            //todo че за ролес? че за гет ролес
            Set<Role> allowRoles = action.getAllowRoles();
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
            //todo тут какой-то замес с пользователями, с ним нужно разобраться
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
                action.setAuthorizedUser(user);
                String errorMessage = (String) session.getAttribute("SecurityFilterMessage");
                if (errorMessage != null) {
                    httpRequest.setAttribute("message", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }
            boolean canExecute = allowRoles == null;
            if (user != null) {
                userName = "\"" + user.getLogin() + "\" user";
                canExecute = canExecute || allowRoles.contains(user.getRole);
            }
            if (canExecute) {
                filterChain.doFilter(request, response);
            } else {
                log.info("Trying of %s access to forbidden resource \"%s\"");
                if (session != null && action.getClass() != MainAction.class) {
                    session.setAttribute("SecurityFilterMessage", "Access is denied"); //todo make internalization
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
