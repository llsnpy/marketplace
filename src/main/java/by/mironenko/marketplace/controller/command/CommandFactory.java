package by.mironenko.marketplace.controller.command;

import by.mironenko.marketplace.controller.command.impl.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger log = Logger.getLogger(CommandFactory.class);
    public static final CommandFactory instance = new CommandFactory();
    private final Map<CommandName, Command> commands;

    {
        commands = initCommands();
    }

    public CommandFactory() { }

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

    private Map<CommandName, Command> initCommands() {
        Map<CommandName, Command> map = new HashMap<>();
        map.put(CommandName.CHOOSE_LANGUAGE, new LanguagesCommandImpl());
        map.put(CommandName.ENTER_INFO_ABOUT_BUYER, new AssignValuesBuyerCommandImpl());
        map.put(CommandName.REGISTER, new RegisterUserCommandImpl());
        map.put(CommandName.LOGIN, new LoginCommandImpl());
        map.put(CommandName.ABOUT_US, new AboutUsCommandImpl());
        map.put(CommandName.CONTACTS, new ContactsCommandImpl());
        map.put(CommandName.SUPPORT, new SupportCommandImpl());
        return map;
    }
}
