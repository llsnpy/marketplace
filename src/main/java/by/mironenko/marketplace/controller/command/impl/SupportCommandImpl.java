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
 * Controller for going to the "Support" tab
 */
public class SupportCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(SupportCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command SUPPORT...");
        ServletContext servletContext = request.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/WEB-INF/jsp/support.jsp");
        requestDispatcher.forward(request, response);
    }
}
