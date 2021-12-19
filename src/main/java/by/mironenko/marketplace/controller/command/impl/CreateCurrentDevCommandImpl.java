package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Developer;
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

public class CreateCurrentDevCommandImpl implements Command {
    private static final Logger log = LogManager.getLogger(CreateCurrentDevCommandImpl.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.debug("<-CONTROLLER-> Executing command DEV_VALUES...");

        GameService gameService = ServiceFactory.getInstance().getGameService();
        DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
        Long id = (Long) request.getSession().getAttribute("id");
        String name = request.getParameter("name");
        double money = Double.parseDouble(request.getParameter("money"));

        try {
            if (developerService.findByName(name) == null) {
                Developer developer = new Developer(id, name, money);
                developerService.create(developer);
                request.setAttribute("current_dev_games", gameService.findByDeveloperId(id));
                request.setAttribute("dev", developerService.findById(id));
                request.getSession().setAttribute("currentUser", developerService.findById(id));
                request.setAttribute("developer", developer);
                request.getRequestDispatcher("/WEB-INF/jsp/dev_cabinet.jsp").forward(request, response);
            } else {
                request.setAttribute("error_message", "Wrong input parameters for creating developer. Try again.");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error("Exception during creating developer: ", e);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
