package uol.compass.mshistory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import uol.compass.mshistory.builders.HistoryBuilder;
import uol.compass.mshistory.models.entities.OrderPublisherData;
import uol.compass.mshistory.models.entities.HistoryEntity;
import uol.compass.mshistory.models.response.HistoryResponseDTO;
import uol.compass.mshistory.repositories.HistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceImplTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistoryRepository historyRepository;

    @Test
    void shouldCreateHistory_success() {
        OrderPublisherData order = HistoryBuilder.getOrderPublisherData();
        HistoryEntity history = HistoryBuilder.getHistoryEntity();

        Mockito.when(historyRepository.save(any())).thenReturn(history);
        historyService.create(order);

        verify(historyRepository).save(any());
    }

    @Test
    void shouldFindAllHistory_success() {
        HistoryEntity historyEntity = HistoryBuilder.getHistoryEntity();
        HistoryResponseDTO historyEntityResponse = HistoryBuilder.getHistoryResponse();

        List<HistoryResponseDTO> expectedHistoryResponseList = List.of(historyEntityResponse);

        Mockito.when(historyRepository.findAll((Sort) any())).thenReturn(List.of(historyEntity));

        List<HistoryResponseDTO> historyResponseList = historyService.findAll(null, null);

        assertEquals(historyResponseList, expectedHistoryResponseList);
    }

    @Test
    void shouldFindHistoryOrderDateBetween_success() {
        HistoryEntity historyEntity = HistoryBuilder.getHistoryEntity();
        HistoryResponseDTO historyEntityResponse = HistoryBuilder.getHistoryResponse();

        List<HistoryResponseDTO> expectedHistoryResponseList = List.of(historyEntityResponse);

        Mockito.when(historyRepository.findByOrderDateBetween(any(), any())).thenReturn(List.of(historyEntity));

        List<HistoryResponseDTO> historyResponseList = historyService.findAll(
                LocalDateTime.of(2023, 01, 01, 13, 30),
                LocalDateTime.of(2023, 01, 02, 13, 30));

        assertEquals(historyResponseList, expectedHistoryResponseList);
    }



}
