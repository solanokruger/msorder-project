package uol.compass.msorder.model.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import uol.compass.msorder.model.entities.ItemEntity;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @CPF
    private String cpf;

    @NotNull
    private List<ItemEntity> items;

    @NotNull
    private double total;

    @NotNull
    private String cep;

    @NotNull
    private String complemento;

}
