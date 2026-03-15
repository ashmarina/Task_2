package pojo.order;

import lombok.Getter;

import java.util.List;

/**
 * Класс для обработки тела ответа API создания заказа POST /orders
 */
@Getter
public class OrderResponse {
    private Boolean success;
    private String message;
    private String name;
    private Order order;

    @Getter
    public static class Order {
        private List<Ingredient> ingredients;
        private String id;
        private Owner owner;
        private String status;
        private String name;
        private String createdAt;
        private String updatedAt;
        private Integer number;
        private Integer price;
    }

    @Getter
    public static class Owner {
        private String name;
        private String email;
        private String createdAt;
        private String updatedAt;
    }

    @Getter
    public static class Ingredient {
        private String id;
        private String name;
        private String type;
        private Integer proteins;
        private Integer fat;
        private Integer carbohydrates;
        private Integer calories;
        private Integer price;
        private String image;
        private String image_mobile;
        private String image_large;
        private Integer __v;
    }
}