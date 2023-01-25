package uol.compass.msorder.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.msorder.model.entities.AddressEntity;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private String cpf;

    private List<ItemResponseDTO> items;

    private double total;

    private AddressEntity address;

}
