package uol.compass.msorder.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    public static final Long ID = 1l;

    @Test
    void shouldFindAllOrders_success() {
        OrderEntity order = new OrderEntity();
        Page<OrderEntity> page = new PageImpl<>(List.of(order));
        OrderResponseParameters expectedOrderResponseParameters = getOrderResponseParameters();

        Mockito.when(orderRepository.findAll((Pageable) any())).thenReturn(page);

        OrderResponseParameters orderResponseParameters =
                orderService.findAll(null, any(Pageable.class));

        assertEquals(expectedOrderResponseParameters, orderResponseParameters);
    }

    @Test
    void shouldFindOrderById_success(){
        OrderEntity order = new OrderEntity();
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        OrderResponseDTO response = orderService.findById(ID);

        assertEquals(response, orderResponseDTO);
    }

    @Test
    void shouldDeleteOrder_success() {
        OrderEntity order = new OrderEntity();

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        orderService.delete(ID);
    }

    private OrderResponseParameters getOrderResponseParameters() {
        return OrderResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .orders(List.of(new OrderResponseDTO()))
                .build();
    }

}
