package uol.compass.msorder.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.services.ItemServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itens")
@Log4j2
public class ItemController {

    private final ItemServiceImpl itemService;

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ItemResponseDTO> update(@PathVariable Long id,
                                                  @Valid @RequestBody ItemRequestDTO itemRequestDTO){
        ItemResponseDTO responseDTO =itemService.update(id, itemRequestDTO);
        log.info("Item Updated");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

}
