import steps.AssertSteps;
import base.UserBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.user.UserRequest;
import pojo.user.UserResponse;

import static constant.ConstantErrorMessage.*;
import static constant.ConstantStoryTitle.TITLE_USER_REGISTER;
import static constant.ConstantUrl.*;

public class UserCreateTest extends UserBaseTest {

    @Test
    @Story(TITLE_USER_REGISTER)
    @DisplayName("POST на " + URL_USER_REGISTER + ". Успешная регистрация нового пользователя")
    @Description("Регистрируем нового пользователя через вызов API " + URL_USER_REGISTER)
    void userRegisterSuccessTest() {
        createUser();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyStatus(true, response.body().as(UserResponse.class).getSuccess());
    }

    @Test
    @Story(TITLE_USER_REGISTER)
    @DisplayName("POST на " + URL_USER_REGISTER + ". Невозможно создать двух одинаковых пользователей")
    @Description("Выполняем попытку создания пользователя с данными, " +
            "от уже существующего через вызов API " + URL_USER_REGISTER)
    void userRegisterDuplicateTest() {
        createUser();
        createUser();

        response.then().assertThat().statusCode(403);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(UserResponse.class).getSuccess(),
                ERROR_USER_CREATE_DUPLICATE, response.body().as(UserResponse.class).getMessage());
    }

    @ParameterizedTest
    @Story(TITLE_USER_REGISTER)
    @MethodSource("testdata.UserParameterizedTestData#userRegisterTestData")
    @DisplayName("POST на " + URL_USER_REGISTER + ". Невозможно создать пользователя без заполнения обязательных полей")
    @Description("Выполняем попытку создания пользователя без заполнения обязательных полей, " +
            "через вызов API " + URL_USER_REGISTER)
    void userRegisterNullCredentialsTest(UserRequest userRqBody, String errorMessage) {
        createUser(userRqBody);

        response.then().assertThat().statusCode(403);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(UserResponse.class).getSuccess(),
                errorMessage, response.body().as(UserResponse.class).getMessage());
    }
}