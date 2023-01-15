package uol.compass.msorder.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @CPF(message = "CPF inválido")
    @NotBlank
    private String cpf;

    @NotNull
    private List<ItemRequestDTO> items;

    @NotNull
    private double total;

    @NotNull
    private String cep;

    @NotBlank
    private String complemento;

}
