package uol.compass.msorder.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "address")
@Table(name = "address")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "number")
    @JsonProperty("número")
    private Long number;

    @Column(name = "street")
    @JsonProperty("logradouro")
    private String street;

    @Column(name = "complement")
    @JsonProperty("complemento")
    private String complement;

    @Column(name = "district")
    @JsonProperty("bairro")
    private String district;

    @Column(name = "location")
    @JsonProperty("localidade")
    private String location;

    @Column(name = "uf")
    private String uf;


}
