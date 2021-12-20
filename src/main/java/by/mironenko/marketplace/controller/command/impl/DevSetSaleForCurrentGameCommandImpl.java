package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.DeveloperService;
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
 * Controller for setting a discount and a discount price for
 * specific game
 */
public class DevSetSaleForCurrentGameCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(DevSetSaleForCurrentGameCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command DEV_SET_SALE_FOR_CURRENT_GAME...");

        Developer dev = (Developer) request.getSession().getAttribute("currentUser");
        Long devId = dev.getId();

        try {
            GameService gameService = ServiceFactory.getInstance().getGameService();
            DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
            Game game = (Game) request.getSession().getAttribute("updatedGame");
            game.setPreSale(Boolean.parseBoolean(request.getParameter("saleStatus")));
            game.setSalePrice(Double.parseDouble(request.getParameter("salePrice")));
            gameService.update(game);

            request.setAttribute("current_dev_games", gameService.findByDeveloperId(devId));
            request.setAttribute("dev", developerService.findById(devId));
            request.getSession().setAttribute("currentUser", developerService.findById(devId));
            request.getRequestDispatcher("/WEB-INF/jsp/dev_cabinet.jsp").forward(request, response);
        } catch (ServiceException e) {
            log.error("Exception during setting sale for game: ", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
