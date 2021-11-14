package by.mironenko.marketplace.controller.command;

import by.mironenko.marketplace.controller.command.impl.common.HomePageCommandImpl;
import by.mironenko.marketplace.controller.command.impl.common.LanguagesCommandImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = initCommands();

    private CommandFactory() { }

    private static Map<String, Command> initCommands() {
        Map<String, Command> commandsList = new HashMap<>();

        commandsList.put("choose_language", new LanguagesCommandImpl());
        /*commandsList.put("register_like_buyer", new RegisterLikeBuyerCommandImpl());
        commandsList.put("register_like_developer", new RegisterLikeDeveloperCommandImpl());
        commandsList.put("login", new LogInCommandImpl());
        commandsList.put("logout", new LogOutCommandImpl());*/


        return commandsList;
    }

    public static Command resolveCommand(final String commandName) {
        return commands.getOrDefault(commandName, new HomePageCommandImpl());
    }
}
