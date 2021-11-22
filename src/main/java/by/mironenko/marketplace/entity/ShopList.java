package by.mironenko.marketplace.entity;

import lombok.*;

import java.util.Date;

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
}
