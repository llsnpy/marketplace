# marketplace

  The application is a trading platform (like steam).

  The application has 3 user roles.
  
  1) Holder (site owner, login - holder, password - holder). There can be only one. Can view all GAME, BUYER, DEVELOPER. Can remove GAME. Sort all lists. It has the ability to sort by the amount of money, by rating, by cost. (In plans: can choose sales of a specific developer, purchases of a specific buyer, sales of games).
  
  2) Developer (developer, login - easports, password - admin). Can develop and update games. Set the price, change the price. Announce discounts and discount prices. (In plans: can sort his games and delete them).

  3) Buyer (buyer, login - buyer, password - buyer). Can buy games, view
information about the game. It has the ability to replenish the balance. (In plans: sort the list of games
alphabetically or by price. Can view the list of developers and sort it alphabetically
or rating).

  The games themselves (GAME) have their own genre and price. The purchase price of the game depends on 
whether a discount is currently set for it.
    
  Each Developer has its own rating (starting different), in the future it can only increase
personalize. An increase in reputation by 1 point is due to the purchase of the developer's game.
  
## database
  postgreSQL
  ![db](https://user-images.githubusercontent.com/74912074/146962556-09fa3f53-3aad-4e06-ac38-63aa9d5cd1ba.png)

## Technologies, libraries and design patterns:
  а) Command
  б) DAO
  в) Singleton
  г) Factory method
  д) Filter
  е) Chain of responsibility
  е) JSP
  ж) Servlet API
  з) JDBC
  и) Bootstrap
  к) Lombok
  л) TestNG
  м) Log4J2
  
## Analogues:
  Steam, Epic games store, Origin
