package by.mironenko.marketplace.entity;

import lombok.*;

import java.sql.Date;

/**
 * @author Pavel Mironenko
 * Game. The main entity that users interact with.
 * Entity. Table - game.
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Game extends Entity {
    private Long id;
    private Long developerId;
    private String name;
    private Genre genre;
    private Date date;
    private double price;
    private boolean preSale;
    private double salePrice;

    public Game(final Long developerId, final String name, final Genre genre, final Date date,
                final double price, final boolean preSale, final double salePrice) {
        this.developerId = developerId;
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.price = price;
        this.preSale = preSale;
        this.salePrice = salePrice;
    }
}
