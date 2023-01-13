package uol.compass.msorder.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.repositories.ItemRepository;

import java.time.LocalDateTime;
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

    public static final Long ID = 1l;

    @Test
    public void shouldUpdateItemTest_success(){
        ItemEntity item = new ItemEntity(
                ID, "test item", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 10, "test description");

        ItemResponseDTO expectedResponse = new ItemResponseDTO(
                ID, "test item", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 10, "test description");

        ItemRequestDTO request = new ItemRequestDTO();

        request.setName("Teste");
        request.setCreationDate(LocalDateTime.now());
        request.setValidationDate(LocalDateTime.now().plusDays(1));
        request.setDescription("Item de teste");

        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(itemRepository.save(any())).thenReturn(item);

        ItemResponseDTO itemResponseDTO = itemService.update(ID, request);

        assertEquals(expectedResponse.getId(), itemResponseDTO.getId());
        verify(itemRepository, times(1)).save(any());
    }

}
