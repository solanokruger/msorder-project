package uol.compass.msorder.services;

import org.springframework.data.domain.Pageable;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO request);

    OrderResponseParameters findAll(String cpf, Pageable pageable);

    OrderResponseDTO findById(Long id);

    OrderResponseDTO update(Long id, OrderRequestUpdateDTO request);

    void delete(Long id);
}
