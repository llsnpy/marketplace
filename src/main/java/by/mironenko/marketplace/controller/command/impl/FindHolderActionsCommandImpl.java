package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.BuyerService;
import by.mironenko.marketplace.service.DeveloperService;
import by.mironenko.marketplace.service.GameService;
import by.mironenko.marketplace.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindHolderActionsCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(FindHolderActionsCommandImpl.class);
    private static final String RESULT_PAGE = "/WEB-INF/jsp/find_result.jsp";

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command HOLDER_ACTION_RES...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
        BuyerService buyerService = ServiceFactory.getInstance().getBuyerService();

        try {
            if (!request.getParameter("game_name").equals("")) {
                request.setAttribute("game", gameService.findByGameName(request.getParameter("game_name")));
                request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
            } else if (!request.getParameter("dev_name").equals("")) {
                request.setAttribute("developer", developerService.findByName(request.getParameter("dev_name")));
                request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
            } else if (!request.getParameter("buyer_surname").equals("")) {
                request.setAttribute("buyer", buyerService.findBySurname(request.getParameter("buyer_surname")));
                request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
            } else {
                request.setAttribute("error_msg_login", "Login is not available. Try again.");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error("Exception during finding values: ", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
