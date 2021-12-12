package by.mironenko.marketplace.controller;

import by.mironenko.marketplace.controller.command.Command;
import by.mironenko.marketplace.controller.command.CommandFactory;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/controller/*")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DispatcherServlet.class);

    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";

    public static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/marketplace";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "LlsnpyMiro123";
    public static final int DB_POOL_START_SIZE = 15;
    public static final int DB_POOL_MAX_SIZE = 100;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public DispatcherServlet() {
        super();
    }

    @Override
    public void init() {
        try {
            log.info("Try to start servlet.");
            Locale.setDefault(Locale.ENGLISH);
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            log.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            log.addAppender(new ConsoleAppender(layout));
            log.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (IOException e) {
            log.error("Impossible to initialize application ", e);
        }
    }

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);

    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        String commandName = (String) request.getAttribute("command");

        Command command = CommandFactory.getInstance().getCommand(commandName);
        command.execute(request, response);
    }
}
