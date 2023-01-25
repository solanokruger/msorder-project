package uol.compass.mshistory.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uol.compass.mshistory.models.response.HistoryResponseDTO;
import uol.compass.mshistory.services.HistoryServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/historico")
@RequiredArgsConstructor
@Log4j2
public class HistoryController {

    private final HistoryServiceImpl historyService;

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false)
                                      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime startDate,
                                  @RequestParam(required = false)
                                      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime endDate){
        List<HistoryResponseDTO> history = historyService.findAll(startDate, endDate);
        log.info("Fetched all history");
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

}
