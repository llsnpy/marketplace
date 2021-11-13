package by.mironenko.marketplace.controller;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.controller.command.Invoker;
import by.mironenko.marketplace.dao.pool.ConnectionPool;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DispatcherServlet.class);

    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";

    public static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/marketplace";
    public static final String DB_USER = "marketHolder";
    public static final String DB_PASSWORD = "LlsnpyMiro123";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 100;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public void init() {
        try {
            log.info("Try to start servlet");
            Logger root = Logger.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (IOException e) {
            log.error("Impossible to initialize application", e);
            destroy();
        }
    }

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        Command command = (Command) request.getAttribute("command");
        Invoker result = command.execute(request);
        result.getCookies().forEach(response::addCookie);
        switch (result.getType()) {
            case FORWARD:
                request.getRequestDispatcher(result.getPath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(result.getPath());
                break;
            default:
                log.error("Cant find invoker type");
                request.getRequestDispatcher("errorpage").forward(request, response);
        }
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
