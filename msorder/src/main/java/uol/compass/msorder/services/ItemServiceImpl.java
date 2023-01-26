package uol.compass.msorder.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.exceptions.InvalidDateException;
import uol.compass.msorder.model.exceptions.InvalidItemValueException;
import uol.compass.msorder.model.exceptions.ItemNotFoundException;
import uol.compass.msorder.model.exceptions.NullDateException;
import uol.compass.msorder.repositories.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
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
        log.info("Update Service Called");
        return modelMapper.map(itemToUpdate, ItemResponseDTO.class);
    }

    public ItemEntity findById(Long id) {
        log.info("FindById Service Called");
         return itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    public void create(List <ItemEntity> items) {
        log.info("Create Service Called");
        items.forEach(itemRepository::save);

    }

    public List<ItemEntity> findAll(){
        log.info("FindAll Service Called");
        return itemRepository.findAll();
    }

    private void validateDate(ItemRequestDTO requestDTO) {
        if(!(requestDTO.getCreationDate().isBefore(requestDTO.getValidationDate()))){
            log.warn("Validate Date Called - Throwed InvalidDateEception");
            throw new InvalidDateException();
        }
    }

    public void validateNullDate(ItemRequestDTO requestDTO){
        if((requestDTO.getCreationDate() == null || requestDTO.getValidationDate() == null)){
            log.warn("Validate Null Date Called - Throwed NullDateException");
            throw new NullDateException();
        }
    }

    public void validateItemValue(ItemRequestDTO requestDTO){
        if (requestDTO.getValue() <= 0){
            log.warn("Validate Item Value Called - Throwed InvalidItemValueException");
            throw new InvalidItemValueException();
        }
    }




}
