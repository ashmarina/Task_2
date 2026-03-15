package steps;

import io.qameta.allure.Step;
import lombok.NoArgsConstructor;

import static constant.ConstantTestFailComment.DEFAULT_ASSERT_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс-обёртка для Assert
 */
@NoArgsConstructor
public class AssertSteps {

    @Step("Проверяем статус в теле ответа")
    public static void checkBodyStatus(Boolean expectedStatus, Boolean actualStatus) {
        assertEquals(expectedStatus, actualStatus, DEFAULT_ASSERT_MESSAGE);
    }

    @Step("Проверяем значение атрибута в теле ответа")
    public static void checkBodyAttributeValue(String expectedMessage, String actualMessage) {
        assertEquals(expectedMessage, actualMessage, DEFAULT_ASSERT_MESSAGE);
    }

    @Step("Проверяем что значение атрибута в теле ответа != NULL")
    public static void checkBodyNotNull(Object object) {
        assertNotNull(object, DEFAULT_ASSERT_MESSAGE);
    }

    @Step("Проверяем статус и текст сообщения в теле ответа")
    public static void checkBodyStatusAndAttribute(
            Boolean expectedStatus, Boolean actualStatus,
            String expectedMessage, String actualMessage) {
        checkBodyStatus(expectedStatus, actualStatus);
        checkBodyAttributeValue(expectedMessage, actualMessage);
    }

    @Step("Проверяем статус и что значение атрибута в теле ответа != NULL")
    public static void checkBodyStatusAndAttributeNotNull(
            Boolean expectedStatus, Boolean actualStatus, Object object) {
        checkBodyStatus(expectedStatus, actualStatus);
        checkBodyNotNull(object);
    }
}