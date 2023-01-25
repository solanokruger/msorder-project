package uol.compass.msorder.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "item")
@Table(name = "item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "validation_date", nullable = false)
    private LocalDateTime validationDate;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "description", nullable = false)
    private String description;
}
