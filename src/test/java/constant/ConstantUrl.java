package constant;

/**
 * Класс для хранения переменных, содержащих путь до API сайта Stellar Burgers
 */
public class ConstantUrl {
    public static final String URL_BASE = "https://stellarburgers.education-services.ru";
    public static final String URL_BASE_PATH = "/api";
    public static final String URL_BASE_AUTH_PATH = URL_BASE_PATH + "/auth";

    public static final String URL_USER_REGISTER = URL_BASE_AUTH_PATH + "/register";
    public static final String URL_USER_LOGIN = URL_BASE_AUTH_PATH + "/login";
    public static final String URL_USER = URL_BASE_AUTH_PATH + "/user";

    public static final String URL_ORDERS = URL_BASE_PATH + "/orders";
}