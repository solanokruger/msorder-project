package uol.compass.msorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.msorder.model.entities.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
