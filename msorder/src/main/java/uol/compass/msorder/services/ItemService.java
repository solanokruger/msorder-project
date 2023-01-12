package uol.compass.msorder.services;

import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;

import java.util.List;

public interface ItemService {

  //  ItemEntity getItemById(Long id);

    void create(List<ItemEntity> item);
}
