package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.GameService;
import by.mironenko.marketplace.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pavel Mironenko
 * @see Command
 * Controller for going to the page for setting discount information
 */
public class DevSetSaleCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(DevSetSaleCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command DEV_GET_SALE...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        Game updatedGame = gameService.findByGameName(request.getParameter("saleGameName"));
        try {
            request.getSession().setAttribute("updatedGame", updatedGame);
            request.setAttribute("updatedGame", updatedGame);
            request.getRequestDispatcher("/WEB-INF/jsp/set_sale_page.jsp").forward(request, response);
        } catch (ServiceException e) {
            log.error("Exception during setting sale: ", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
