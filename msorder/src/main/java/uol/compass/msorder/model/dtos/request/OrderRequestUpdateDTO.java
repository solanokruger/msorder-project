package uol.compass.msorder.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestUpdateDTO {

    @CPF
    private String cpf;

    @NotNull
    private String cep;

    @NotNull
    private String complemento;

}
