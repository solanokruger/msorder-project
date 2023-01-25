package uol.compass.mshistory.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("Erro interno do servidor"),
    JSON_PROCESSING_ERROR("Erro ao processar o JSON");

    private final String message;
}
