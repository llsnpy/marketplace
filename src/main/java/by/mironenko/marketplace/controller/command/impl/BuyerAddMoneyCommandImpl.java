package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.BuyerService;
import by.mironenko.marketplace.service.GameService;
import by.mironenko.marketplace.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyerAddMoneyCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(BuyerAddMoneyCommandImpl.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command BUYER_ADD_MONEY...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        BuyerService buyerService = ServiceFactory.getInstance().getBuyerService();

        try {
            if (Double.parseDouble(request.getParameter("sum")) <= 0) {
                throw new ServletException("Incorrect sum to add.");
            } else {
                Buyer buyer = (Buyer) request.getSession().getAttribute("currentUser");

                buyer.setMoney(buyer.getMoney() + Double.parseDouble(request.getParameter("sum")));

                buyerService.update(buyer);

                request.setAttribute("games", gameService.findAll());
                request.setAttribute("current_buyer_games", gameService.findByBuyerID(buyer.getId()));
                request.setAttribute("buyer", buyerService.findById(buyer.getId()));
                request.getSession().setAttribute("currentUser", buyerService.findById(buyer.getId()));
                request.getRequestDispatcher("/WEB-INF/jsp/buyer_cabinet.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error("Exception during adding money", e);
            request.setAttribute("error_message", "Error during add money to buyer account.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
