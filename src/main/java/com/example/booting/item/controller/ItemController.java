package com.example.booting.item.controller;


import com.example.booting.item.entity.Item;
import com.example.booting.exception.ApiErrorResponse;
import com.example.booting.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@Tag(name = "Items", description = "Item management APIs")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @Operation(
            summary = "Create item",
            description = "Creates a new item."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item created"),
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
    @PostMapping
    public Item create(
            @org.springframework.web.bind.annotation.RequestBody
            @RequestBody(
                    required = true,
                    description = "Item creation payload",
                    content = @Content(schema = @Schema(implementation = Item.class))
            )
            Item item
    ){
        return service.saveItem(item);
    }

    @Operation(
            summary = "List items",
            description = "Returns all items."
    )
    @ApiResponse(responseCode = "200", description = "Items returned")
    @GetMapping
    public List<Item> getAll(){
        return service.findAll();
    }
}
