package by.mironenko.marketplace.controller.command;

/**
 * @author Pavel Mironenko
 * @see CommandFactory
 * @see by.mironenko.marketplace.controller.filter.CommandFilter
 * Enum witn all command. Eqch command corresponds to
 * its own URL coming from the filter
 */
public enum CommandName {
    CHOOSE_LANGUAGE,
    ABOUT_US,
    CONTACTS,
    SUPPORT,
    MAIN,
    LOG_IN,
    REGISTRATION,
    REG,
    DEV_VALUES,
    BUYER_VALUES,
    FIND_HOLDER_ACTION_RES,
    SORT_HOLDER_ACTION,
    HOLDER_ACTIONS,
    LOG_OUT,
    DEV_CREATE_NEW_GAME,
    DEV_UPDATE_GAME,
    DEV_SET_SALE,
    BUYER_ADD_MONEY,
    BUYER_BUY_GAME,
    DEV_UPDATE_CURRENT_GAME,
    DEV_SET_SALE_FOR_CURRENT_GAME,
    WRONG_REQUEST,
    LANGUAGE
}
