package uol.compass.msorder.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity(name = "orders")
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "item_id")
    private List<ItemEntity> items;

    @Column(name = "total")
    private double total;

    @JoinColumn(name = "address_id")
    @OneToOne
    private AddressEntity addressEntity;

}
