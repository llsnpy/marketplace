package by.mironenko.marketplace.entity;

import lombok.*;

/**
 * @author Pavel Mironenko
 * Role - Developer (1/3). Developer has name, wallet, 2 lists (developing and released)
 * of games. Developer can 1) develop new game and upload it to marketplace. 2) View the
 * list of products placed on the market. 3) announce a pre-order and in the future to
 * provide a discount.
 */
@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Developer extends Entity {
    private Long id;
    private String name;
    private double money;
    private int rating;

    public Developer(final Long id, final String name, final Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
        rating = 0;
    }
}
