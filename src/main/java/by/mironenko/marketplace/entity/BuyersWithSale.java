package by.mironenko.marketplace.entity;

import lombok.*;

/**
 * @author Pavel Mironenko
 * Entity. Table - buyers_with_sale.
 * Not used in this realisation of application
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class BuyersWithSale extends Entity {
    private Long id;
    private Long buyerId;
    private Long gameId;
}
