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
    ORDER_NOT_FOUND("Pedido não encontrado! "),
    INVALID_DATE_PARAMETER("A data de criação deve ser anterior a data de validade!"),
    NULL_DATE_PARAMETER("As datas de criação e de validade não podem ser nulas!"),
    JSON_PROCESSING_ERROR("Erro ao processar o JSON"),
    INVALID_ITEM_VALUE("O valor do item deve ser maior que zero!");

    private final String message;
}
