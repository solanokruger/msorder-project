package uol.compass.mshistory.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uol.compass.mshistory.models.entities.HistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntity, String> {

    List<HistoryEntity> findAll(Sort orderDate);

    List<HistoryEntity> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
