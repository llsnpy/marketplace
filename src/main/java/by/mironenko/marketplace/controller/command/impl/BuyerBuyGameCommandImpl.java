package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.entity.ShopList;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class BuyerBuyGameCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(BuyerBuyGameCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command BUYER_BUY_GAME...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        PurchaseService purchaseService = ServiceFactory.getInstance().getPurchaseService();
        BuyerService buyerService = ServiceFactory.getInstance().getBuyerService();
        DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();

        try {
            Buyer buyer = (Buyer) request.getSession().getAttribute("currentUser");
            Game game = gameService.findByGameName(request.getParameter("gameForBuying"));
            Developer developer = developerService.findById(gameService.getDeveloperId(game.getId()));

            Double price = null;
            if (game.isPreSale()) {
                price = game.getSalePrice();
            } else {
                price = game.getPrice();
            }

            if (price < buyer.getMoney()) {
                long shopListId = purchaseService.findMaxId() + 1;
                LocalDate localDate = LocalDate.now();
                Date buyDate = Date.valueOf(localDate);

                ShopList shopList = new ShopList(shopListId, buyer.getId(), game.getId(), buyDate, price);

                buyer.setMoney(buyer.getMoney() - price);
                developer.setRating(developer.getRating() + 1);
                developer.setMoney(developer.getMoney() + price);
                buyerService.update(buyer);
                developerService.update(developer);
                purchaseService.create(shopList);

                request.setAttribute("games", gameService.findAll());
                request.setAttribute("current_buyer_games", gameService.findByBuyerID(buyer.getId()));
                request.setAttribute("buyer", buyer);
                request.getSession().setAttribute("currentUser", buyer);
                request.getRequestDispatcher("/WEB-INF/jsp/buyer_cabinet.jsp").forward(request, response);
            } else {
                request.setAttribute("error_message", "Wrong input parameters for game creating.");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error("Exception during buy game", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
