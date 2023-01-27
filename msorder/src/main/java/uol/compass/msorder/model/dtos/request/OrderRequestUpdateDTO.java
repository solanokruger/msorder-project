package uol.compass.msorder.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestUpdateDTO {

    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "(\\d{8})", message = "Parâmetro inválido, informe apenas 8 números!")
    private String cep;

    @NotNull
    private Long number;

}
