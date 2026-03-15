package pojo.order;

import lombok.*;

import java.util.List;

/**
 * Класс для обработки тела запроса API создания заказа /orders
 */
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Setter
@Getter
@Builder(toBuilder = true)
@ToString
public class OrderRequest {
    private List<String> ingredients;
}