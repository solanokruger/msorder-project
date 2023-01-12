package uol.compass.msorder.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.services.AddressService;
import uol.compass.msorder.services.OrderServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class OrderController {

    private final AddressService addressService;

    private final OrderServiceImpl orderService;

//    @GetMapping("/{cep}")
//    public ResponseEntity findAddress(@PathVariable String cep){
//        return ResponseEntity.ok().body(addressService.findAddress(cep));
//    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody @Valid OrderRequestDTO requestDTO){
        OrderResponseDTO orderResponseDTO = orderService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @GetMapping
    public ResponseEntity<OrderResponseParameters> findAll(
            @RequestParam(required = false) String cpf,
            Pageable pageable){

        OrderResponseParameters orderResponse = orderService.findAll(cpf, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable("id") Long id){
        OrderResponseDTO orderResponseDTO = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> delete(@PathVariable("id") Long id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
