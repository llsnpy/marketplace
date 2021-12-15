package by.mironenko.marketplace.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ShopList extends Entity {
    private Long id;
    private Long buyerId;
    private Long gameId;
    private Date date;
    private double price;

    public ShopList(final Long buyerId, final Long gameId,
                    final Date date, final double price) {
        this.buyerId = buyerId;
        this.gameId = gameId;
        this.date = date;
        this.price = price;
    }
}
