package uol.compass.msorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.msorder.model.entities.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
