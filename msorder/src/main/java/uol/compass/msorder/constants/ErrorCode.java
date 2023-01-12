package uol.compass.msorder.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("Pedido inválido"),
    INVALID_PARAMETER("Paramêtro do pedido inválido"),

    INTERNAL_SERVER_ERROR("Erro interno do servidor"),
    ITEM_NOT_FOUND("Item não encontrado!"),
    ORDER_NOT_FOUND("Pedido não encontrado! ");

    private final String message;
}
