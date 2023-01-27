package uol.compass.msorder.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import uol.compass.msorder.model.entities.ItemEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private List<ItemEntity> items;

    @NotNull
    private double total;

    @NotBlank
    @Pattern(regexp = "(\\d{8})", message = "Parâmetro inválido, informe apenas 8 números!")
    private String cep;

    @NotNull
    private Long number;

}
