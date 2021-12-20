package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pavel Mironenko
 * @see Command
 * Controller for going to the "About us" tab
 */
public class AboutUsCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(AboutUsCommandImpl.class);

    /**
     * @param request  incoming request
     * @param response incoming request
     * @throws ServletException exception throwing on error
     * @throws IOException exception throwing on error
     */
    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command ABOUT_US...");
        ServletContext servletContext = request.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/WEB-INF/jsp/about.jsp");
        requestDispatcher.forward(request, response);
    }
}
