import steps.AssertSteps;
import base.OrdersBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.order.*;

import static constant.ConstantErrorMessage.ERROR_ORDERS_EMPTY_INGREDIENTS_LIST;
import static constant.ConstantStoryTitle.TITLE_ORDERS_CREATE;
import static constant.ConstantUrl.URL_ORDERS;

public class OrdersCreateTest extends OrdersBaseTest {

    @Test
    @Story(TITLE_ORDERS_CREATE)
    @DisplayName("POST на " + URL_ORDERS + ". Успешное создание нового заказа без авторизации")
    @Description("Создаём новый заказ через вызов API " + URL_ORDERS)
    void ordersCreateUnauthorisedSuccessTest() {
        createOrder();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyStatus(true, response.body().as(OrderResponse.class).getSuccess());
    }

    @Test
    @Story(TITLE_ORDERS_CREATE)
    @DisplayName("POST на " + URL_ORDERS + ". Успешное создание нового заказа c авторизацией")
    @Description("Создаём новый заказ через вызов API " + URL_ORDERS)
    void ordersCreateAuthorisedSuccessTest() {
        createOrderAuthorised();

        response.then().assertThat().statusCode(200);
        AssertSteps.checkBodyStatusAndAttribute(
                true, response.body().as(OrdersListResponse.class).getSuccess(),
                userRqBody.getEmail(), response.body().as(OrderResponse.class).getOrder().getOwner().getEmail());
    }

    @Test
    @Story(TITLE_ORDERS_CREATE)
    @DisplayName("POST на " + URL_ORDERS + ". Невозможно создать заказ c указанием несуществующих ингридиентов")
    @Description("Выполняем попытку создания нового заказа через вызов API " + URL_ORDERS)
    void ordersCreateWrongIngredientsTest() {
        createOrderWrongIngredients();

        response.then().assertThat().statusCode(500);
    }

    @Test
    @Story(TITLE_ORDERS_CREATE)
    @DisplayName("POST на " + URL_ORDERS + ". Невозможно создать заказ без указания существующих ингридиентов")
    @Description("Выполняем попытку создания нового заказа через вызов API " + URL_ORDERS)
    void ordersCreateNullIngredientsTest() {
        createOrderNullIngredients();

        response.then().assertThat().statusCode(400);
        AssertSteps.checkBodyStatusAndAttribute(
                false, response.body().as(OrderResponse.class).getSuccess(),
                ERROR_ORDERS_EMPTY_INGREDIENTS_LIST, response.body().as(OrderResponse.class).getMessage());
    }
}