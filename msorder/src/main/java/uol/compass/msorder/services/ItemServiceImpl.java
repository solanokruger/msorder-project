package uol.compass.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.exceptions.ItemNotFoundException;
import uol.compass.msorder.repositories.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

//    @Override
//    public ItemEntity getItemById(Long id) {
//        return itemRepository.findById(id)
//                .orElseThrow(ItemNotFoundException::new);
//    }

    public void create(List <ItemEntity> item) {
        for (int i = 0; i < item.size(); i++) {
            itemRepository.save(item.get(i));
        }
    }

    public List<ItemEntity> findAll(){
        return itemRepository.findAll();
    }

}
