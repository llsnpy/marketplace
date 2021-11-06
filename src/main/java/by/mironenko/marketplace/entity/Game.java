package by.mironenko.marketplace.entity;

import lombok.*;

import java.util.Date;

/**
 * @author Pavel Mironenko
 * Game. The main entity that users interact with.
 * You can view, buy and create game.
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Game extends Entity {
    private Long id;
    private String name;
    private Genre genre;
    private Date date;
    private double price;
    private boolean preSale;
    private double salePrice;

    public Game(final String name, final Genre genre, final Date date,
                final double price, final boolean preSale, final double salePrice) {
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.price = price;
        this.preSale = preSale;
        this.salePrice = salePrice;
    }
}
