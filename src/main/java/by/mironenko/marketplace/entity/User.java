package by.mironenko.marketplace.entity;

import lombok.*;

/**
 * @author Pavel Mironenko.
 * Entity. Table - users.
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User extends Entity {
    private Long id;
    private String login;
    private String password;
    private String role;

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
