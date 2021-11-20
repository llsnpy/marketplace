package by.mironenko.marketplace.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pavel Mironenko
 *
 * Describes interface of a command that executes
 * depending on the requeted URL
 */
public interface Command {

   void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
