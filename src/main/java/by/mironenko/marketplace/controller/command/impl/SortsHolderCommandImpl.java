package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
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
import java.util.List;

public class SortsHolderCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(SortsHolderCommandImpl.class);
    private static final String HOLDER_PAGE = "/WEB-INF/jsp/holder_cabinet.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BuyerService service = ServiceFactory.getInstance().getBuyerService();
        List<Buyer> buyers = service.sortByMoney();

        DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
        List<Developer> developers = developerService.sortByRating();

        GameService gameService = ServiceFactory.getInstance().getGameService();
        List<Game> games = gameService.sortByPrice();

        request.setAttribute("buyers", buyers);
        request.setAttribute("developers", developers);
        request.setAttribute("games", games);

        try {
            if (request.getParameter("sort_by_price") != null) {
                request.setAttribute("games", games);
                request.getRequestDispatcher(HOLDER_PAGE).forward(request, response);
            } else if (request.getParameter("sort_by_rating") != null) {
                request.setAttribute("developers", developers);
                request.getRequestDispatcher(HOLDER_PAGE).forward(request, response);
            } else if (request.getParameter("sort_by_money") != null) {
                request.setAttribute("buyers", buyers);
                request.getRequestDispatcher(HOLDER_PAGE).forward(request, response);
            } else {
                request.setAttribute("error_msg_login", "Login is not available. Try again.");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error("Exception during sorting values: ", e);

        }
    }
}
