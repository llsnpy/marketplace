package by.mironenko.marketplace.controller.command;

import by.mironenko.marketplace.controller.command.impl.AssignValuesBuyerCommandImpl;
import by.mironenko.marketplace.controller.command.impl.LanguagesCommandImpl;
import by.mironenko.marketplace.controller.command.impl.LoginCommandImpl;
import by.mironenko.marketplace.controller.command.impl.RegisterUserCommandImpl;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger log = Logger.getLogger(CommandFactory.class);
    public static final CommandFactory instance = new CommandFactory();
    private static final Map<CommandName, Command> commands = new HashMap<>();

    public CommandFactory() {
        commands.put(CommandName.CHOOSE_LANGUAGE, new LanguagesCommandImpl());
        commands.put(CommandName.ENTER_INFO_ABOUT_BUYER, new AssignValuesBuyerCommandImpl());
        commands.put(CommandName.REGISTER, new RegisterUserCommandImpl());
        commands.put(CommandName.LOGIN, new LoginCommandImpl());
    }

    public Command getCommand(final String name) {
        log.debug("Execute command starting. Name-> " + name);

        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            log.debug("Command name-> " + commandName);
            command = commands.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error(e.getMessage(), e);
            command = commands.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

    public static CommandFactory getInstance() {
        return instance;
    }
}
