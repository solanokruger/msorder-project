package uol.compass.msorder.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uol.compass.msorder.builders.OrderBuilder;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.services.AddressService;
import uol.compass.msorder.services.ItemServiceImpl;
import uol.compass.msorder.services.OrderServiceImpl;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {


    @Spy
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private AddressService addressService;

    @MockBean
    private ItemServiceImpl itemService;

    public static final String BASE_URL = "/api/pedidos";

    public static final String ID_URL = BASE_URL + "/1";

    @Test
    void create() throws Exception{
        OrderRequestDTO request = OrderBuilder.getOrderRequest();
        OrderResponseDTO responseDTO = OrderBuilder.getOrderResponse();

        when(orderService.create(any())).thenReturn(responseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void findAll() throws Exception {
        OrderResponseParameters orderResponseParameters = OrderBuilder.getOrderResponseParameters();

        when(orderService.findAll(any(), any())).thenReturn(orderResponseParameters);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        OrderResponseDTO orderResponseDTO = OrderBuilder.getOrderResponse();

        when(orderService.findById(any())).thenReturn(orderResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception{
        OrderRequestUpdateDTO orderRequestUpdate = OrderBuilder.getOrderRequestUpdateDTO();
        OrderResponseDTO responseDTO = OrderBuilder.getOrderResponse();

        when(orderService.update(any(), any())).thenReturn(responseDTO);

        String input = TestUtils.mapToJson(orderRequestUpdate);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }


}
