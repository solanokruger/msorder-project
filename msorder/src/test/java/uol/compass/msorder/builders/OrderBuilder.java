package uol.compass.msorder.builders;

import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.AddressEntity;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.entities.OrderEntity;

import java.util.List;

public class OrderBuilder {

    public static final Long ID = 1L;
    public static final String CPF = "348.068.489-05";
    public static final List<ItemEntity> ITEM_LIST = List.of(ItemBuilder.getItemEntity());
    public static final List<ItemResponseDTO> ITEM_RESPONSE_LIST = List.of(ItemBuilder.getItemResponse());
    public static final double TOTAL = 10;
    public static final AddressEntity ADDRESS = AddressBuilder.getAddress();
    public static final String CEP = "98290000";
    public static final Long NUMBER = 123L;

    public static OrderEntity getOrderEntity(){
        return OrderEntity.builder()
                .id(ID)
                .cpf(CPF)
                .items(ITEM_LIST)
                .total(TOTAL)
                .addressEntity(ADDRESS)
                .build();
    }

    public static OrderRequestDTO getOrderRequest(){
        return OrderRequestDTO.builder()
                .cpf(CPF)
                .items(ITEM_LIST)
                .total(TOTAL)
                .cep(CEP)
                .number(NUMBER)
                .build();
    }

    public static OrderResponseDTO getOrderResponse(){
        return OrderResponseDTO.builder()
                .id(ID)
                .total(TOTAL)
                .cpf(CPF)
                .items(ITEM_RESPONSE_LIST)
                .address(ADDRESS)
                .build();
    }

    public static OrderResponseParameters getOrderResponseParameters(){
        return OrderResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(ID)
                .totalPages(1)
                .orders(List.of(getOrderResponse()))
                .build();
    }

    public static OrderRequestUpdateDTO getOrderRequestUpdateDTO(){
        return OrderRequestUpdateDTO.builder()
                .cep(CEP)
                .cpf(CPF)
                .number(NUMBER)
                .build();
    }

}
