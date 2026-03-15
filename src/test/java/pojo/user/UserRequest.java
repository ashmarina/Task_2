package pojo.user;

import lombok.*;

/**
 * Класс для обработки тела запроса API управления пользователем /register, /login и /user
 */
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class UserRequest {
    private String email;
    private String password;
    private String name;
}