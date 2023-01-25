package uol.compass.msorder.builders;

import uol.compass.msorder.model.entities.AddressEntity;

public class AddressBuilder {

    public static AddressEntity getAddress(){
        return AddressEntity.builder()
                .id(1L)
                .cep("98290000")
                .number(123L)
                .street("rua")
                .complement("comp")
                .district("dist")
                .location("local")
                .uf("RS")
                .build();
    }

}
