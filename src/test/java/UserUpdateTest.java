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

import static constant.ConstantErrorMessage.ERROR_USER_UPDATE_DUPLICATE;
import static constant.ConstantStoryTitle.TITLE_USER_UPDATE;
import static constant.ConstantUrl.URL_USER;

public class UserUpdateTest extends UserBaseTest {

    @Test
    @Story(TITLE_USER_UPDATE)
    @DisplayName("PATCH на " + URL_USER + ". Успешное обновление имени пользователя")
    @Description("Обновляем имя пользователя через вызов API " + URL_USER)
    void userUpdateNameSuccessTest() {
        updateUserName();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyAttributeValue(
                userRqBody.getName(),
                response.body().as(UserResponse.class).getUser().getName());
    }

    @Test
    @Story(TITLE_USER_UPDATE)
    @DisplayName("PATCH на " + URL_USER + ". Успешное обновление email адреса пользователя")
    @Description("Обновляем email адрес пользователя через вызов API " + URL_USER)
    void userUpdateEmailSuccessTest() {
        updateUserEmail();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyAttributeValue(
                userRqBody.getEmail(),
                response.body().as(UserResponse.class).getUser().getEmail());
    }

    @Test
    @Story(TITLE_USER_UPDATE)
    @DisplayName("PATCH на " + URL_USER + ". Успешное обновление имени и email адреса пользователя")
    @Description("Обновляем имени и email адрес пользователя через вызов API " + URL_USER)
    void userUpdateNameAndEmailSuccessTest() {
        updateUserNameAndEmail();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyAttributeValue(
                userRqBody.getName(),
                response.body().as(UserResponse.class).getUser().getName());
        AssertSteps.checkBodyAttributeValue(
                userRqBody.getEmail(),
                response.body().as(UserResponse.class).getUser().getEmail());
    }

    @Test
    @Story(TITLE_USER_UPDATE)
    @DisplayName("PATCH на " + URL_USER + ". " +
            "Невозможно обновить email адрес пользователя, если он уже используется в системе")
    @Description("Выполняем попытку обновления email адреса пользователя через вызов API " + URL_USER)
    void userUpdateEmailDuplicateTest() {
        updateUserEmailDuplicate();

        response.then().assertThat().statusCode(403);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(UserResponse.class).getSuccess(),
                ERROR_USER_UPDATE_DUPLICATE, response.body().as(UserResponse.class).getMessage());
    }

    @ParameterizedTest
    @Story(TITLE_USER_UPDATE)
    @MethodSource("testdata.UserParameterizedTestData#userUpdateUnauthorisedTestData")
    @DisplayName("PATCH на " + URL_USER + ". Невозможно обновить атрибуты пользователя без токена авторизации")
    @Description("Выполняем попытку обновления атрибутов пользователя через вызов API " + URL_USER)
    void userUpdateUnauthorisedTest(UserRequest userRqBody, String errorMessage) {
        updateUserUnauthorised(userRqBody);

        response.then().assertThat().statusCode(401);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(UserResponse.class).getSuccess(),
                errorMessage, response.body().as(UserResponse.class).getMessage());
    }
}