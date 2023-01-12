package uol.compass.msorder.model.dtos.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private LocalDateTime creationDate;

    @Nullable
    private LocalDateTime validationDate;

    @NotNull
    private double valor;

    @NotBlank
    private String description;
}
