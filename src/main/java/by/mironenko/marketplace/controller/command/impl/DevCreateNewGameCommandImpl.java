package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.entity.Genre;
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
import java.sql.Date;

public class DevCreateNewGameCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(DevCreateNewGameCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command DEV_CREATE_NEW_GAME...");

        Developer dev = (Developer) request.getSession().getAttribute("currentUser");
        String gameName = request.getParameter("gameName");
        Long devId = dev.getId();
        Genre gameGenre = Genre.valueOf(request.getParameter("genre").toUpperCase());
        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        boolean saleStatus = Boolean.parseBoolean(request.getParameter("saleStatus"));
        double salePrice = Double.parseDouble(request.getParameter("salePrice"));
        
        //todo проверку на жаннр, в случае неправильных данных перенаправлять на страницу с ошибкой и с кнопкой назад
        //todo и вообще все ошибки перенаправлять на страницу с ошибкой и делать там кнопку назад
        try {
            if (gameName != null || price > 0.0) {
                GameService gameService = ServiceFactory.getInstance().getGameService();
                DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
                Game game = new Game(devId, gameName, gameGenre, releaseDate, price, saleStatus, salePrice);
                gameService.create(game);

                request.setAttribute("current_dev_games", gameService.findByDeveloperId(devId));
                request.setAttribute("dev", developerService.findById(devId));
                request.getSession().setAttribute("currentUser", developerService.findById(devId));
                request.getRequestDispatcher("/WEB-INF/jsp/dev_cabinet.jsp").forward(request, response);
            } else {

                //todo на другую страницу перенаправлять
                request.setAttribute("error_msg_login", "Wrong input parameters for game creating.");
                request.getRequestDispatcher("login").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error(e);
        }
    }
}
