package uol.compass.msorder.model.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private LocalDateTime validationDate;

    @NotNull
    private double value;

    @NotBlank
    private String description;
}
