package uol.compass.msorder.builders;

import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.entities.ItemEntity;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class ItemBuilder {

    public static final Long ID = 1L;
    public static final String NAME = "item test";

    public static final LocalDateTime CREATION_DATE =
            LocalDateTime.of(2023, 01, 01, 13, 30, 10);
    public static final LocalDateTime VALIDATION_DATE =
            LocalDateTime.of(2023, 01, 02, 13, 30, 10);
    public static final double VALUE = 10;
    public static final String DESCRIPTION = "item description test";

    public static ItemEntity getItemEntity(){
        return ItemEntity.builder()
                .id(ID)
                .name(NAME)
                .creationDate(CREATION_DATE)
                .validationDate(VALIDATION_DATE)
                .value(VALUE)
                .description(DESCRIPTION)
                .build();
    }

    public static ItemRequestDTO getItemRequest(){
        return ItemRequestDTO.builder()
                .name(NAME)
                .creationDate(CREATION_DATE)
                .validationDate(VALIDATION_DATE)
                .value(VALUE)
                .description(DESCRIPTION)
                .build();
    }

    public static ItemResponseDTO getItemResponse(){
        return ItemResponseDTO.builder()
                .id(ID)
                .name(NAME)
                .creationDate(CREATION_DATE)
                .validationDate(VALIDATION_DATE)
                .value(VALUE)
                .description(DESCRIPTION)
                .build();
    }


}
