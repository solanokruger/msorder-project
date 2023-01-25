package uol.compass.mshistory.services;

import org.springframework.stereotype.Service;
import uol.compass.mshistory.models.entities.OrderPublisherData;
import uol.compass.mshistory.models.entities.HistoryEntity;
import uol.compass.mshistory.models.response.HistoryResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface HistoryService {

    List<HistoryResponseDTO> findAll(LocalDateTime startDate, LocalDateTime endDate);

    HistoryEntity create(OrderPublisherData orderData);
}
