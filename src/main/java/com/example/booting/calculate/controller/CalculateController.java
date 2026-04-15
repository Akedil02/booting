package com.example.booting.calculate.controller;

import com.example.booting.exception.ApiErrorResponse;
import com.example.booting.calculate.service.CalculateService;
import com.example.booting.calculate.dto.CalculateRequest;
import com.example.booting.calculate.dto.CalculateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Calculate", description = "Calculation APIs")
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @Operation(
            summary = "Calculate sum",
            description = "Adds two numbers and returns the result."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculation completed"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    @PostMapping("/api/calculate")
    public CalculateResponse calculate(
            @org.springframework.web.bind.annotation.RequestBody
            @RequestBody(
                    required = true,
                    description = "Calculation payload",
                    content = @Content(schema = @Schema(implementation = CalculateRequest.class))
            )
            CalculateRequest request
    ){
        return calculateService.add(request);
    }

}
