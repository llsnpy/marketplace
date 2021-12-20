package by.mironenko.marketplace.entity;

import lombok.*;

/**
 * @author Pavel Mironenko
 * Entity. Table - "buyer"
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
