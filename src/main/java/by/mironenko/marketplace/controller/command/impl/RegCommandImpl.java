package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.ServiceFactory;
import by.mironenko.marketplace.service.UserService;
import by.mironenko.marketplace.service.coding.PasswordCoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(RegCommandImpl.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command REG...");
        PasswordCoder pc = new PasswordCoder();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = request.getParameter("login");
        String password = pc.encrypt(request.getParameter("password"));
        String role = request.getParameter("role");
        try {
            if (userService.findByLogin(login) == null) {
                User user = new User(login, password, role);
                userService.create(user);

                long id = userService.findId(login);
                HttpSession session = request.getSession();
                session.setAttribute("id", id);

                switch (user.getRole()) {
                    case "buyer":
                        request.getRequestDispatcher("/WEB-INF/jsp/input_buyer.jsp").forward(request, response);
                        break;
                    case "dev":
                        request.getRequestDispatcher("/WEB-INF/jsp/input_dev.jsp").forward(request, response);
                        break;
                    default:
                        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error_msg_login", "Login is not available. Try again.");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error(e);
        }
    }
}
