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

import static constant.ConstantStoryTitle.TITLE_USER_LOGIN;
import static constant.ConstantUrl.URL_USER_LOGIN;

public class UserLoginTest extends UserBaseTest {

    @Test
    @Story(TITLE_USER_LOGIN)
    @DisplayName("POST на " + URL_USER_LOGIN + ". Успешная авторизация пользователя")
    @Description("Авторизируемся пользователем через вызов API " + URL_USER_LOGIN)
    void userLoginSuccessTest() {
        loginUser();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyStatus(true, response.body().as(UserResponse.class).getSuccess());
    }

    @ParameterizedTest
    @Story(TITLE_USER_LOGIN)
    @MethodSource("testdata.UserParameterizedTestData#userLoginTestData")
    @DisplayName("POST на " + URL_USER_LOGIN + ". " +
            "Невозможно авторизоваться пользователем c несуществующими данными или без заполнения обязательных полей")
    @Description("Выполняем попытку авторизации пользователя с некрректными данными, " +
            "через вызов API " + URL_USER_LOGIN)
    void userLoginNullCredentialsTest(UserRequest userRqBody, String errorMessage) {
        loginUser(userRqBody);

        response.then().assertThat().statusCode(401);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(UserResponse.class).getSuccess(),
                errorMessage, response.body().as(UserResponse.class).getMessage());
    }
}