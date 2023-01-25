package uol.compass.mshistory.controllers;

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
import uol.compass.mshistory.builders.HistoryBuilder;
import uol.compass.mshistory.models.response.HistoryResponseDTO;
import uol.compass.mshistory.services.HistoryServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = HistoryController.class)
public class HistoryControllerTest {

    @Spy
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HistoryServiceImpl historyService;

    private static final String BASE_URL = "/api/historico";

    @Test
    void findAll() throws Exception{
        HistoryResponseDTO historyResponse = HistoryBuilder.getHistoryResponse();

        when(historyService.findAll(any(), any())).thenReturn(List.of(historyResponse));

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
