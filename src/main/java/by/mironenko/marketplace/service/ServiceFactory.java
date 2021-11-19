package by.mironenko.marketplace.service;

import by.mironenko.marketplace.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public final GameService gameService = new GameServiceImpl();
    public final BuyerService buyerService = new BuyerServiceImpl();
    public final DeveloperService developerService = new DeveloperServiceImpl();
    public final BuyerTakeSale buyerTakeSale = new BuyerTakeSaleImpl();
    public final PurchaseService purchaseService = new PurchaseServiceImpl();
    public final UserService userService = new UserServiceImpl();

   private ServiceFactory() { }

   public static ServiceFactory getInstance() {
       return instance;
   }

   public GameService getGameService() {
       return gameService;
   }

   public BuyerService getBuyerService() {
       return buyerService;
   }

   public DeveloperService getDeveloperService() {
       return developerService;
   }

   public BuyerTakeSale getBuyerTakeSale() {
       return buyerTakeSale;
   }

   public PurchaseService getPurchaseService() {
       return purchaseService;
   }

   public UserService getUserService() {
       return userService;
   }
}
