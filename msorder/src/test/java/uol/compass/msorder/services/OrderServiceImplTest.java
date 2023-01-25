package uol.compass.msorder.services;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import uol.compass.msorder.builders.AddressBuilder;
import uol.compass.msorder.builders.OrderBuilder;
import uol.compass.msorder.infra.mqueue.OrderPublisher;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.repositories.AddressRepository;
import uol.compass.msorder.repositories.ItemRepository;
import uol.compass.msorder.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private OrderPublisher orderPublisher;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    public static final Long ID = 1L;

    @Test
    void shouldCreateOrder_success() throws JsonProcessingException {
        OrderEntity order = OrderBuilder.getOrderEntity();
        OrderRequestDTO orderRequest = OrderBuilder.getOrderRequest();
        OrderResponseDTO expectedResponse = OrderBuilder.getOrderResponse();

        Mockito.when(addressService.findAddress(any())).thenReturn(AddressBuilder.getAddress());
        Mockito.when(orderRepository.save(any())).thenReturn(order);

        OrderResponseDTO orderResponse = orderService.create(orderRequest);

        assertEquals(orderResponse, expectedResponse);
    }

    @Test
    void shouldFindAllOrders_success() {
        OrderEntity order = OrderBuilder.getOrderEntity();
        Page<OrderEntity> page = new PageImpl<>(List.of(order));
        OrderResponseParameters expectedOrderResponseParameters = OrderBuilder.getOrderResponseParameters();

        Mockito.when(orderRepository.findAll((Pageable) any())).thenReturn(page);

        OrderResponseParameters orderResponseParameters =
                orderService.findAll(null, any(Pageable.class));

        assertEquals(expectedOrderResponseParameters, orderResponseParameters);
    }

    @Test
    void shouldFindOrderById_success(){
        OrderEntity order = OrderBuilder.getOrderEntity();
        OrderResponseDTO orderResponseDTO = OrderBuilder.getOrderResponse();

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        OrderResponseDTO response = orderService.findById(ID);

        assertEquals(response, orderResponseDTO);
    }

    @Test
    void shouldUpdateOrder_success(){
        OrderEntity order = OrderBuilder.getOrderEntity();
        OrderResponseDTO orderResponse = OrderBuilder.getOrderResponse();
        OrderRequestUpdateDTO orderRequestUpdate = OrderBuilder.getOrderRequestUpdateDTO();

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        Mockito.when(addressService.findAddress(any())).thenReturn(AddressBuilder.getAddress());
        Mockito.when(orderRepository.save(any())).thenReturn(order);

        OrderResponseDTO response = orderService.update(ID, orderRequestUpdate);

        assertEquals(orderResponse, response);
    }

    @Test
    void shouldDeleteOrder_success() {
        OrderEntity order = OrderBuilder.getOrderEntity();

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        orderService.delete(ID);
        verify(orderRepository).deleteById(any());
    }

}
