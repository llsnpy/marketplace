package by.mironenko.marketplace.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Mironenko
 *
 * Describes interface of a command that executes
 * depending on the requeted URL
 */
public interface Command {

    Invoker execute(HttpServletRequest request);
}
