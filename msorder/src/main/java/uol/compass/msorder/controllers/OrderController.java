package uol.compass.msorder.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.services.AddressService;
import uol.compass.msorder.services.OrderServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
@Log4j2
public class OrderController {

    private final AddressService addressService;
    private final OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody OrderRequestDTO requestDTO) throws JsonProcessingException {
        OrderResponseDTO orderResponseDTO = orderService.create(requestDTO);
        log.info("Order Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @GetMapping
    public ResponseEntity<OrderResponseParameters> findAll(
            @RequestParam(required = false) String cpf,
            Pageable pageable){

        OrderResponseParameters orderResponse = orderService.findAll(cpf, pageable);
        log.info("Finded all Orders");
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable("id") Long id){
        OrderResponseDTO orderResponseDTO = orderService.findById(id);
        log.info("Finded Order by ID");
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id,
                                                   @RequestBody @Valid OrderRequestUpdateDTO request) {
        OrderResponseDTO responseDTO = orderService.update(id, request);
        log.info("Order Updated");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> delete(@PathVariable("id") Long id){
        orderService.delete(id);
        log.info("Order Deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
