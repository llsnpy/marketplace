package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.*;
import by.mironenko.marketplace.service.coding.PasswordCoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pavel Mironenko
 * @see Command
 * Controller for entering the application based on
 * username and password
 */
public class LoginCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommandImpl.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command LOG_IN...");
        PasswordCoder passwordCoder = new PasswordCoder();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = userService.findByLogin(login);
            if (!passwordCoder.isMatch(password, user.getPassword())) {
                request.setAttribute("error_msg", "User not found");
            }
            if (userService.isExist(login, password)) {

                BuyerService buyerService = ServiceFactory.getInstance().getBuyerService();
                DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
                GameService gameService = ServiceFactory.getInstance().getGameService();

                request.setAttribute("buyers", buyerService.findAll());
                request.setAttribute("developers", developerService.findAll());
                request.setAttribute("games", gameService.findAll());

                switch (user.getRole()) {
                    case "holder":
                        request.getSession().setAttribute("currentUser", user);
                        request.getRequestDispatcher("/WEB-INF/jsp/holder_cabinet.jsp").forward(request, response);
                        break;
                    case "buyer":
                        request.setAttribute("current_buyer_games", gameService.findByBuyerID(user.getId()));
                        request.setAttribute("buyer", buyerService.findById(user.getId()));
                        request.getSession().setAttribute("currentUser", buyerService.findById(user.getId()));
                        request.getRequestDispatcher("/WEB-INF/jsp/buyer_cabinet.jsp").forward(request, response);
                        break;
                    case "dev":
                        request.setAttribute("current_dev_games", gameService.findByDeveloperId(user.getId()));
                        request.setAttribute("dev", developerService.findById(user.getId()));
                        request.getSession().setAttribute("currentUser", developerService.findById(user.getId()));
                        request.getRequestDispatcher("/WEB-INF/jsp/dev_cabinet.jsp").forward(request, response);
                        break;
                    default:
                        request.getRequestDispatcher("/WEB-INF/index.jsp");
                }
            } else {
                request.setAttribute("error_message", "User not found, reenter input data.");
                request.getRequestDispatcher("error").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error("Exception during log in: ", e);
            request.setAttribute("error_message", "User not found, reenter input data.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
