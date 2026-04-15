package com.example.booting.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Info", description = "Project info APIs")
public class InfoController {


    @Operation(
            summary = "Get project info",
            description = "Returns static information about the student and course."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Info returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    value = "{\"student\":\"Nurserik Akedil\",\"course\":\"Backend Framework: Spring\",\"week\":1}"
                            )
                    )
            )
    })
    @GetMapping("/api/info")
    public Map<String, Object> getInfo(){
        Map<String, Object> response = new HashMap<>();
        response.put("student", "Nurserik Akedil");
        response.put("course", "Backend Framework: Spring");
        response.put("week", 1);
        return response;
    }
}
