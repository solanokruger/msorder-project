package uol.compass.msorder.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.response.ItemResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.services.AddressService;
import uol.compass.msorder.services.OrderServiceImpl;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private AddressService addressService;

    public static final String BASE_URL = "/api/pedidos";

    public static final String ID_URL = BASE_URL + "/1";

    @Test
    void create() throws Exception{
        OrderRequestDTO request = getOrderRequestDTO();
        OrderResponseDTO responseDTO = new OrderResponseDTO();

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
        OrderResponseParameters orderResponseParameters = new OrderResponseParameters();

        when(orderService.findAll(any(), any())).thenReturn(orderResponseParameters);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON))
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

    @Test
    void findById() throws Exception {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        when(orderService.findById(any())).thenReturn(orderResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    private OrderRequestDTO getOrderRequestDTO() {
        List<ItemEntity> items;

        return OrderRequestDTO.builder()
                .cpf("031.090.920-18")
                .total(55)
                .items(new ArrayList<ItemEntity>())
                .cep("98270000")
                .complemento("123")
                .build();
    }


}
