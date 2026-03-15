import steps.AssertSteps;
import base.OrdersBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.order.OrdersListResponse;

import static constant.ConstantErrorMessage.ERROR_USER_UNAUTHORIZED;
import static constant.ConstantStoryTitle.TITLE_ORDERS_GET_USER_LIST;
import static constant.ConstantUrl.URL_ORDERS;

public class OrdersGetUserListTest extends OrdersBaseTest {

    @Test
    @Story(TITLE_ORDERS_GET_USER_LIST)
    @DisplayName("GET на " + URL_ORDERS + ". Невозможно получить список заказов конкретного пользователя без авторизации")
    @Description("Выполняем попытку получения списка заказов через вызов API " + URL_ORDERS)
    void ordersGetUserListUnauthorisedTest() {
        getUserListUnauthorised();

        response.then().assertThat().statusCode(401);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(OrdersListResponse.class).getSuccess(),
                ERROR_USER_UNAUTHORIZED, response.body().as(OrdersListResponse.class).getMessage());
    }

    @Test
    @Story(TITLE_ORDERS_GET_USER_LIST)
    @DisplayName("GET на " + URL_ORDERS + ". Получение списка заказов конкретного пользователя")
    @Description("Выполняем попытку получения списка заказов через вызов API " + URL_ORDERS)
    void ordersGetUserListAuthorisedTest() {
        getUserListAuthorised();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyStatusAndAttributeNotNull(
                true, response.body().as(OrdersListResponse.class).getSuccess(),
                response.body().as(OrdersListResponse.class).getOrders());
    }
}