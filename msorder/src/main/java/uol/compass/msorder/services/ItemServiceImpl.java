package uol.compass.msorder.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.exceptions.InvalidDateException;
import uol.compass.msorder.model.exceptions.ItemNotFoundException;
import uol.compass.msorder.repositories.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    public ItemResponseDTO update(Long id, ItemRequestDTO requestDTO){
        validateDate(requestDTO);

        ItemEntity itemToUpdate = findById(id);

        itemToUpdate.setName(requestDTO.getName());
        itemToUpdate.setValue(requestDTO.getValue());
        itemToUpdate.setDescription(requestDTO.getDescription());
        itemToUpdate.setCreationDate(requestDTO.getCreationDate());
        itemToUpdate.setValidationDate(requestDTO.getValidationDate());

        itemRepository.save(itemToUpdate);

        return modelMapper.map(itemToUpdate, ItemResponseDTO.class);
    }

    private void validateDate(ItemRequestDTO requestDTO) {
        if(!(requestDTO.getCreationDate().isBefore(requestDTO.getValidationDate()))){
            throw new InvalidDateException();
        }
    }

    public ItemEntity findById(Long id) {
         return itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    public void create(List <ItemEntity> items) {
        items.forEach(itemRepository::save);
    }

    public List<ItemEntity> findAll(){
        return itemRepository.findAll();
    }





}
