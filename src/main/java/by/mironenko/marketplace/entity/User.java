package by.mironenko.marketplace.entity;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends Entity {
    private Long id;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
