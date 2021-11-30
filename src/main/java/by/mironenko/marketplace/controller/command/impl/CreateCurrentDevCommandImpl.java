package by.mironenko.marketplace.controller.command.impl;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.DeveloperService;
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
        DeveloperService developerService = ServiceFactory.getInstance().getDeveloperService();
        Long id = (Long) request.getSession().getAttribute("id");
        String name = request.getParameter("name");
        double money = Double.parseDouble(request.getParameter("money"));

        try {
            if (developerService.findByName(name) == null) {
                Developer developer = new Developer(id, name, money);
                developerService.create(developer);
                request.setAttribute("developer", developer);
                request.getRequestDispatcher("/WEB-INF/jsp/dev_cabinet.jsp").forward(request, response);
            } else {
                request.setAttribute("error_msg_login", "Login is not available. Try again.");
                request.getRequestDispatcher("login").forward(request, response);
            }
        } catch (ServiceException e) {
            log.error(e);
        }
    }
}
