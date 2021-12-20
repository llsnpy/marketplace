package by.mironenko.marketplace.entity;

import lombok.*;

/**
 * @author Pavel Mironenko
 * Entity. Table - developer
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
