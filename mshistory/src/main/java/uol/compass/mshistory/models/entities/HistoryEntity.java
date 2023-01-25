package uol.compass.mshistory.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEntity {

    @Id
    private String id;

    private Long idOrder;

    private double orderTotal;

    private LocalDateTime orderDate;




}
