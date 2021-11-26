package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
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
import java.util.List;

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

                BuyerService service = ServiceFactory.getInstance().getBuyerService();
                List<Buyer> buyers = service.findAll();

                DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
                List<Developer> developers = developerService.findAll();

                GameService gameService = ServiceFactory.getInstance().getGameService();
                List<Game> games = gameService.findAll();

                request.setAttribute("buyers", buyers);
                request.setAttribute("developers", developers);
                request.setAttribute("games", games);
                switch (user.getRole()) {
                    case "holder":
                        request.getRequestDispatcher("/WEB-INF/jsp/holder_cabinet.jsp").forward(request, response);
                        break;
                    case "buyer":
                        request.getRequestDispatcher("/WEB-INF/jsp/buyer_cabinet.jsp").forward(request, response);
                        break;
                    case "dev":
                        request.getRequestDispatcher("/WEB-INF/jsp/dev_cabinet.jsp").forward(request, response);
                        break;
                    default:
                        request.getRequestDispatcher("/WEB-INF/index.jsp");
                }
            } else {
                request.setAttribute("error_msg_login", "User not found, reenter input data.");
                request.getRequestDispatcher("login").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error(e);
        }
    }
}
