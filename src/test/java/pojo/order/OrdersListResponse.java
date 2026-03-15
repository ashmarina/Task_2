package pojo.order;

import lombok.Getter;

import java.util.List;

/**
 * Класс для обработки тела ответа API просмотра заказа GET /orders
 */
@Getter
public class OrdersListResponse {
    private Boolean success;
    private String message;
    private List<Order> orders;
    private Integer total;
    private Integer totalToday;

    @Getter
    private static class Order {
        private List<String> ingredients;
        private String id;
        private String status;
        private Integer number;
        private String createdAt;
        private String updatedAt;
    }
}