package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Buyer;
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

public class CreateCurrentBuyerCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(CreateCurrentBuyerCommandImpl.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command BUYER_VALUES...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        BuyerService buyerService = ServiceFactory.getInstance().getBuyerService();
        Long id = (Long) request.getSession().getAttribute("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double money = Double.parseDouble(request.getParameter("money"));
        int age = Integer.parseInt(request.getParameter("age"));

        try {
            Buyer newBuyer = new Buyer(id, name, surname, money, age);
            buyerService.create(newBuyer);

            request.setAttribute("games", gameService.findAll());
            request.setAttribute("current_buyer_games", gameService.findByBuyerID(id));
            request.setAttribute("buyer", buyerService.findById(id));
            request.getSession().setAttribute("currentUser", buyerService.findById(id));

            request.getRequestDispatcher("/WEB-INF/jsp/buyer_cabinet.jsp").forward(request, response);
        } catch (ServiceException e) {
            log.error("Exception during creating new buyer: ", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
