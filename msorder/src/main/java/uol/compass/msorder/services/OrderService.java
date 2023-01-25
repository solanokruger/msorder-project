package uol.compass.msorder.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;

@Service
public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO request) throws JsonProcessingException;

    OrderResponseParameters findAll(String cpf, Pageable pageable);

    OrderResponseDTO findById(Long id);

    OrderResponseDTO update(Long id, OrderRequestUpdateDTO request);

    void delete(Long id);
}
