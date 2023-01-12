package uol.compass.msorder.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "street")
    private String logradouro;

    @Column(name = "complement")
    private String complemento;

    @Column(name = "district")
    private String bairro;

    @Column(name = "location")
    private String localidade;

    @Column(name = "uf")
    private String uf;


}
