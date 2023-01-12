package uol.compass.msorder.services;

import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;

import java.util.List;

public interface ItemService {

    void create(List<ItemEntity> item);
    ItemResponseDTO update(Long id, ItemRequestDTO requestDTO);

}
