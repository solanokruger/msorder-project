package uol.compass.msorder.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.msorder.builders.AddressBuilder;
import uol.compass.msorder.builders.ItemBuilder;
import uol.compass.msorder.builders.OrderBuilder;
import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.repositories.ItemRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    public static final Long ID = 1L;

    @Test
    void shouldCreateItem_success() {
        ItemEntity item = ItemBuilder.getItemEntity();

        Mockito.when(itemRepository.save(any())).thenReturn(item);

        itemService.create(List.of(item));

        verify(itemRepository).save(any());
    }

    @Test
    void shouldFindAll_success() {
        ItemEntity item = ItemBuilder.getItemEntity();
        List<ItemEntity> expectedResponse = List.of(item);

        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemEntity> response = itemService.findAll();

        assertEquals(response, expectedResponse);
        verify(itemRepository).findAll();
    }

    @Test
    public void shouldUpdateItemTest_success(){
        ItemEntity item = ItemBuilder.getItemEntity();
        ItemResponseDTO expectedResponse = ItemBuilder.getItemResponse();
        ItemRequestDTO request = ItemBuilder.getItemRequest();

        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(itemRepository.save(any())).thenReturn(item);

        ItemResponseDTO itemResponseDTO = itemService.update(ID, request);

        assertEquals(expectedResponse.getId(), itemResponseDTO.getId());
        verify(itemRepository, times(1)).save(any());
    }

}
