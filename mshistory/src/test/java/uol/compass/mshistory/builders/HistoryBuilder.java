package uol.compass.mshistory.builders;

import lombok.Builder;
import uol.compass.mshistory.models.entities.OrderPublisherData;
import uol.compass.mshistory.models.entities.HistoryEntity;
import uol.compass.mshistory.models.response.HistoryResponseDTO;

import java.time.LocalDateTime;

@Builder
public class HistoryBuilder {

    private static final String ID = "1";
    private static final Long ID_ORDER = 1L;
    private static final LocalDateTime DATE = LocalDateTime.of(2023, 01, 01, 13, 30);
    private static final double ORDER_TOTAL = 1;
    private static final Long ID_ORDER_DATA = 1L;
    private static final double ORDER_DATA_TOTAL = 10;

    public static OrderPublisherData getOrderPublisherData(){
        return OrderPublisherData.builder()
                .id(ID_ORDER_DATA)
                .total(ORDER_DATA_TOTAL)
                .build();
    }

    public static HistoryEntity getHistoryEntity(){
        return HistoryEntity.builder()
                .id(ID)
                .idOrder(ID_ORDER)
                .orderDate(DATE)
                .orderTotal(ORDER_TOTAL)
                .build();
    }

    public static HistoryResponseDTO getHistoryResponse(){
        return HistoryResponseDTO.builder()
                .id(ID)
                .idOrder(ID_ORDER)
                .orderDate(DATE)
                .orderTotal(ORDER_TOTAL)
                .build();
    }

}
