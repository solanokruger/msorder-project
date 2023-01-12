package uol.compass.msorder.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "item")
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    @Column(name = "value")
    private double value;

    @Column(name = "description")
    private String description;
}
