package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.service.GameService;
import by.mironenko.marketplace.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DevUpdateGameCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(DevUpdateGameCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command DEV_UPDATE_GAME...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        Game updatedGame = gameService.findByGameName(request.getParameter("updatedGameName"));
        try {
            request.getSession().setAttribute("updatedGame", updatedGame);
            request.setAttribute("updatedGame", updatedGame);
            request.getRequestDispatcher("/WEB-INF/jsp/updatedGamePage.jsp").forward(request, response);
        } catch (ServletException e) {
            log.error("Exception during updating game: ", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
