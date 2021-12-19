package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HolderActionsCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(HolderActionsCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command HOLDER_ACTIONS...");

        BuyerService buyerService = ServiceFactory.getInstance().getBuyerService();
        DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
        GameService gameService = ServiceFactory.getInstance().getGameService();
        PurchaseService purchaseService = ServiceFactory.getInstance().getPurchaseService();

        try {
            if (request.getParameter("gameName") != null) {
                purchaseService.delete(gameService.findByGameName(request.getParameter("gameName")).getId());
                gameService.delete(gameService.findByGameName(request.getParameter("gameName")).getId());
                request.setAttribute("buyers", buyerService.findAll());
                request.setAttribute("developers", developerService.findAll());
                request.setAttribute("games", gameService.findAll());
                request.getRequestDispatcher("/WEB-INF/jsp/holder_cabinet.jsp").forward(request, response);
            } else {
                request.setAttribute("error_msg_login", "User not found, reenter input data.");
                request.getRequestDispatcher("login").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error(e);
        }
    }
}
