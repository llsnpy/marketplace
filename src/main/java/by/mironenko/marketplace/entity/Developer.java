package by.mironenko.marketplace.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
public class Developer extends Entity {
    private Long id;
    private String name;
    private double money;
    private int rating;
    private List<Game> developingGames;
    private List<Game> releasedGames;

    private Developer(final String name, final Double money) {
        this.name = name;
        this.money = money;
        rating = 0;
        developingGames = new ArrayList<>(); //todo work with lists, mb not necessary there
        releasedGames = new ArrayList<>();
    }

    //todo отдельные конструктор с играми, рейтингом
    //todo методы для добавления и перемещения игр

}
