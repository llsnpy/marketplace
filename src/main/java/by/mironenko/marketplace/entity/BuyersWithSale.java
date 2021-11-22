package by.mironenko.marketplace.entity;

import lombok.*;

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
