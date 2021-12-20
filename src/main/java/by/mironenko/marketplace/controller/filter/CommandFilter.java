package by.mironenko.marketplace.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.controller.command.CommandFactory
 * This filter processes the incoming request, determining the name
 * of the command that should be called. The command name is then
 * passed to the CommandFactory.
 */
public class CommandFilter implements Filter {
    private static final Logger log = Logger.getLogger(CommandFilter.class);

    private static Map<String, String> commands;

    static {
        commands = initCommandsList();
    }

    /**
     * @param servletRequest request
     * @param servletResponse response
     * @param filterChain filter (for next filtratino)
     * @throws IOException exception
     * @throws ServletException exception
     * The method based oon the request, gives the appropriate command to be executed.
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            log.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginCommand = contextPath.length();
            int endCommand = uri.lastIndexOf('/');
            String commandName;
            if (endCommand >= 0) {
                commandName = uri.substring(endCommand + 1);
            } else {
                commandName = uri.substring(beginCommand);
            }
            String commandClass = commands.get(commandName);
            try {
               httpRequest.setAttribute("command", commandClass);
               filterChain.doFilter(servletRequest, servletResponse);
            } catch (NullPointerException e) {
                log.error("Impossible to create command handler object", e);
                e.printStackTrace();
                httpRequest.setAttribute("error", "The requested address could not be called");
                httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            log.error("Impossible to use HTTP filter");
            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    /**
     * @return map of all commands
     */
    private static Map<String, String> initCommandsList() {
        Map<String, String> commands = new HashMap<>();
        commands.put("choose_language", "LANGUAGE");
        commands.put("about_us", "ABOUT_US");
        commands.put("contacts", "CONTACTS");
        commands.put("support", "SUPPORT");
        commands.put("main", "MAIN");
        commands.put("log_in", "LOG_IN");
        commands.put("registration", "REGISTRATION");
        commands.put("reg", "REG");
        commands.put("dev_values", "DEV_VALUES");
        commands.put("buyer_values", "BUYER_VALUES");
        commands.put("find_holder_action_res", "FIND_HOLDER_ACTION_RES");
        commands.put("holder_sorts", "SORT_HOLDER_ACTION");
        commands.put("holder_actions", "HOLDER_ACTIONS");
        commands.put("logout", "LOG_OUT");
        commands.put("buyer_add_money", "BUYER_ADD_MONEY");
        commands.put("buyer_buy_game", "BUYER_BUY_GAME");
        commands.put("dev_set_sale", "DEV_SET_SALE");
        commands.put("dev_create_new_game", "DEV_CREATE_NEW_GAME");
        commands.put("dev_update_game", "DEV_UPDATE_GAME");
        commands.put("dev_update_current_game", "DEV_UPDATE_CURRENT_GAME");
        commands.put("dev_set_sale_for_current_game", "DEV_SET_SALE_FOR_CURRENT_GAME");
        return commands;
    }
}
