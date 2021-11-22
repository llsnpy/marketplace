package by.mironenko.marketplace.controller.filter;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.controller.command.impl.AboutUsCommandImpl;
import by.mironenko.marketplace.controller.command.impl.LanguagesCommandImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandFilter implements Filter {
    private static final Logger log = Logger.getLogger(CommandFilter.class);

    private static Map<String, String> commands;

    static {
        commands = initCommandsList();
    }

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
                commandName = uri.substring(/*beginCommand, */endCommand + 1);
            } else {
                commandName = uri.substring(beginCommand);
            }
            String commandClass = commands.get(commandName);
            try {
               httpRequest.setAttribute("command", commandClass);
               filterChain.doFilter(servletRequest, servletResponse);
            } catch (NullPointerException e) {
                log.error("Impossible to create command handler object", e);
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
                httpRequest.setAttribute("error", String.format("The requested address could not be called"));
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

    private static Map<String, String> initCommandsList() {
        Map<String, String> commands = new HashMap<>();
        commands.put("choose_language", "LANGUAGE");
        commands.put("about_us", "ABOUT_US");
        commands.put("contacts", "CONTACTS");
        commands.put("support", "SUPPORT");
        commands.put("main", "MAIN");
        return commands;
    }
}
