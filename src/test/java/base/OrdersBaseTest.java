package base;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import pojo.order.OrderRequest;

import java.util.List;

import static constant.ConstantIngredients.*;
import static constant.ConstantUrl.*;
import static testdata.TestDataProvider.*;


public class OrdersBaseTest extends UserBaseTest {
    protected OrderRequest orderRqBody = new OrderRequest();

    @Step("Создаём тестовые данные перед выполнением теста")
    @BeforeEach
    void setUp() {
        super.setUp();
        orderRqBody = orderRqBody.toBuilder()
                .ingredients(List.of(INGREDIENT_BUN_R2_D3, INGREDIENT_MEAT_PROTOSTOMIA, INGREDIENT_SAUCE_SPICY_X))
                .build();
        Allure.step("Создаём тестовый заказ: " + orderRqBody.toString());
    }

    public void createOrder() {
        response = httpManager.post(URL_ORDERS, orderRqBody);
    }

    public void createOrderWrongIngredients() {
        orderRqBody.setIngredients(List.of(generateRandomUuid()));
        response = httpManager.post(URL_ORDERS, orderRqBody);
    }

    public void createOrderNullIngredients() {
        orderRqBody.setIngredients(null);
        response = httpManager.post(URL_ORDERS, orderRqBody);
    }

    public void createOrderAuthorised() {
        createUser();
        response = httpManager.post(URL_ORDERS, orderRqBody, userToken);
    }

    public void getUserListUnauthorised() {
        response = httpManager.get(URL_ORDERS);
    }

    public void getUserListAuthorised() {
        createOrderAuthorised();
        response = httpManager.get(URL_ORDERS, userToken);
    }
}