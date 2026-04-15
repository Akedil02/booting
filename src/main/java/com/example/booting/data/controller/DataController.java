package com.example.booting.data.controller;

import com.example.booting.data.service.DataService;
import com.example.booting.data.dto.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Data", description = "External data APIs")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @Operation(
            summary = "Get data",
            description = "Returns data fetched by the data service."
    )
    @ApiResponse(responseCode = "200", description = "Data returned")
    @GetMapping("/api/data")
    public DataResponse getData() {
        return dataService.getData();
    }
}
