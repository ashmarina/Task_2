package testdata;

import org.junit.jupiter.params.provider.Arguments;
import pojo.user.UserRequest;

import java.util.stream.Stream;

import static constant.ConstantErrorMessage.*;

/**
 * Класс для формирования тестовых данных для API управления пользователем /register, /login и /user
 */
public class UserParameterizedTestData extends TestDataProvider {
    private static final UserRequest USER_RQ_BODY = new UserRequest();

    private static Stream<Arguments> userRegisterTestData() {
        return Stream.of(
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(null)
                                .password(generateRandomPassword())
                                .name(generateRandomName())
                                .build(),
                        ERROR_USER_CREATE_NULL_CREDENTIALS
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(generateRandomEmail())
                                .password(null)
                                .name(generateRandomName())
                                .build(),
                        ERROR_USER_CREATE_NULL_CREDENTIALS
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(generateRandomEmail())
                                .password(generateRandomPassword())
                                .name(null)
                                .build(),
                        ERROR_USER_CREATE_NULL_CREDENTIALS
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(null)
                                .password(null)
                                .name(null)
                                .build(),
                        ERROR_USER_CREATE_NULL_CREDENTIALS
                )
        );
    }

    private static Stream<Arguments> userLoginTestData() {
        return Stream.of(
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(generateRandomEmail())
                                .password(generateRandomPassword())
                                .build(),
                        ERROR_USER_LOGIN_NULL_CREDENTIALS
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(null)
                                .password(generateRandomPassword())
                                .build(),
                        ERROR_USER_LOGIN_NULL_CREDENTIALS
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(generateRandomEmail())
                                .password(null)
                                .build(),
                        ERROR_USER_LOGIN_NULL_CREDENTIALS
                )
        );
    }

    private static Stream<Arguments> userUpdateUnauthorisedTestData() {
        return Stream.of(
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(generateRandomEmail())
                                .name(generateRandomName())
                                .build(),
                        ERROR_USER_UNAUTHORIZED
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(null)
                                .name(generateRandomName())
                                .build(),
                        ERROR_USER_UNAUTHORIZED
                ),
                Arguments.of(USER_RQ_BODY.toBuilder()
                                .email(generateRandomEmail())
                                .name(null)
                                .build(),
                        ERROR_USER_UNAUTHORIZED
                )
        );
    }
}