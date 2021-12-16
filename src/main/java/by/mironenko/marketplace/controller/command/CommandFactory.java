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
        map.put(CommandName.ABOUT_US, new AboutUsCommandImpl());
        map.put(CommandName.CONTACTS, new ContactsCommandImpl());
        map.put(CommandName.SUPPORT, new SupportCommandImpl());
        map.put(CommandName.MAIN, new MainPageCommandImpl());
        map.put(CommandName.LOG_IN, new LoginCommandImpl());
        map.put(CommandName.REGISTRATION, new RegisterCommandImpl());
        map.put(CommandName.REG, new RegCommandImpl());
        map.put(CommandName.DEV_VALUES, new CreateCurrentDevCommandImpl());
        map.put(CommandName.BUYER_VALUES, new CreateCurrentBuyerCommandImpl());
        map.put(CommandName.FIND_HOLDER_ACTION_RES, new FindHolderActionsCommandImpl());
        map.put(CommandName.SORT_HOLDER_ACTION, new SortsHolderCommandImpl());
        map.put(CommandName.HOLDER_ACTIONS, new HolderActionsCommandImpl());
        map.put(CommandName.LOG_OUT, new LogoutCommandImpl());
        map.put(CommandName.DEV_CREATE_NEW_GAME, new DevCreateNewGameCommandImpl());
        map.put(CommandName.DEV_UPDATE_GAME, new DevUpdateGameCommandImpl());
        map.put(CommandName.DEV_GET_SALE, new DevGetSaleCommandImpl());
        map.put(CommandName.BUYER_ADD_MONEY, new BuyerAddMoneyCommandImpl());
        map.put(CommandName.BUYER_BUY_GAME, new BuyerBuyGameCommandImpl());
        map.put(CommandName.DEV_UPDATE_CURRENT_GAME, new DevUpdateCurrentGameCommandImpl());
        return map;
    }
}
