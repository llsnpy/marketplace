package by.mironenko.marketplace.entity;

import lombok.*;

/**
 * @author Pavel Mironenko
 * Role - buyer (2/3). Classical user. He can 1) view games. 2) Buy the game.
 * 3) He can participate in the pre-order.
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Buyer extends Entity {
    private Long id;
    private String name;
    private String surname;
    private double money;
    private int age;

}
