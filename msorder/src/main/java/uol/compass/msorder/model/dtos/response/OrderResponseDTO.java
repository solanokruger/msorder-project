package uol.compass.msorder.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.msorder.model.entities.AddressEntity;
import uol.compass.msorder.model.entities.ItemEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private String cpf;

    private List<ItemEntity> items;

    private double total;

    private AddressEntity address;

}
