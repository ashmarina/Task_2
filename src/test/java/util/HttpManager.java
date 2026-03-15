package util;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class HttpManager {
    private static final int DEFAULT_TIME_IN_MILLISECONDS = 5000;

    public HttpManager(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }


    private RestAssuredConfig getConfig() {
        return RestAssuredConfig.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", DEFAULT_TIME_IN_MILLISECONDS)           // Connection timeout
                        .setParam("http.socket.timeout", DEFAULT_TIME_IN_MILLISECONDS)               // Read timeout
                        .setParam("http.connection-manager.timeout", DEFAULT_TIME_IN_MILLISECONDS)  // Connection request timeout
        );
    }


    @Step("Выполняем вызов GET на метод {0}")
    public Response get(String path) {
        return given()
                .contentType(ContentType.JSON)
                .get(path);
    }


    @Step("Выполняем вызов GET на метод {0} с токеном пользователя")
    public Response get(String path, String userToken) {
        return userToken == null ? get(path) :
                given().contentType(ContentType.JSON)
                        .and().auth().oauth2(userToken)
                        .get(path);
    }


    @Step("Выполняем вызов POST на метод {0}")
    public Response post(String path, Object body) {
        return given().config(getConfig()).contentType(ContentType.JSON).and().body(body).when().post(path);
    }


    @Step("Выполняем вызов POST на метод {0}")
    public Response post(String path, Object body, String userToken) {
        return userToken == null ? post(path, body) :
                given().config(getConfig()).contentType(ContentType.JSON)
                        .and().auth().oauth2(userToken)
                        .and().body(body)
                        .when().post(path);
    }


    @Step("Выполняем вызов PATCH на метод {0}")
    public Response httpPatch(String path, Object body) {
        return given().config(getConfig()).contentType(ContentType.JSON).and().body(body).when().patch(path);
    }


    @Step("Выполняем вызов PATCH на метод {0} с токеном пользователя")
    public Response httpPatch(String path, Object body, String userToken) {
        return userToken == null ? httpPatch(path, body) :
                given().config(getConfig())
                        .contentType(ContentType.JSON)
                        .and().auth().oauth2(userToken)
                        .and().body(body)
                        .when().patch(path);
    }


    @Step("Выполняем вызов DELETE на метод {0}")
    public void httpDelete(String path, String userToken) {
        given().config(getConfig()).auth().oauth2(userToken).delete(path);
    }
}