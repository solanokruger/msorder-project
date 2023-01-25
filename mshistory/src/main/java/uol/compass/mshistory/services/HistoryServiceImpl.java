package uol.compass.mshistory.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uol.compass.mshistory.models.entities.OrderPublisherData;
import uol.compass.mshistory.models.entities.HistoryEntity;
import uol.compass.mshistory.models.response.HistoryResponseDTO;
import uol.compass.mshistory.repositories.HistoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class HistoryServiceImpl implements HistoryService{

    private final ModelMapper modelMapper;

    private final HistoryRepository historyRepository;

    public List<HistoryResponseDTO> findAll(LocalDateTime startDate, LocalDateTime endDate){
        List<HistoryEntity> list;

        if (startDate == null || endDate == null){
             list = historyRepository.findAll(Sort.by("orderDate").descending());
        }else{
            list = historyRepository.findByOrderDateBetween(startDate, endDate);
        }
        log.info("FindAll Service Called");
        return mapList(list);
    }

    public HistoryEntity create(OrderPublisherData orderData){
        HistoryEntity history = new HistoryEntity();
        history.setOrderDate(LocalDateTime.now());
        history.setIdOrder(orderData.getId());
        history.setOrderTotal(orderData.getTotal());

        historyRepository.save(history);
        log.info("Create Service Called");
        return history;
    }

    public List<HistoryResponseDTO> mapList(List<HistoryEntity> entities){
        log.info("List mapped to HistoryResponse List");
        return entities
                .stream()
                .map(history -> modelMapper.map(history, HistoryResponseDTO.class))
                .collect(Collectors.toList());
    }

}
