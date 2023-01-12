package uol.compass.msorder.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.repositories.OrderRepository;

import java.util.Optional;

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
    void shouldDeleteOrder_sucess() {
        OrderEntity order = new OrderEntity();

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        orderService.delete(ID);
    }

}
