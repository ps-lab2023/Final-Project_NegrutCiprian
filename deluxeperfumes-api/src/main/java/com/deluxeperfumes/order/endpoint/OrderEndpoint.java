package com.deluxeperfumes.order.endpoint;


import com.deluxeperfumes.order.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/order")
public interface OrderEndpoint {

    @Operation(summary = "Create order")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Create order")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = {
                    @Content(schema = @Schema(implementation = OrderDto.class))
            }
    )
    @PostMapping
    OrderDto createOrder(@org.springframework.web.bind.annotation.RequestBody OrderDto orderDto);

    @Operation(summary = "Update order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Order was updated")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = {
                    @Content(schema = @Schema(implementation = OrderDto.class))
            }
    )
    @PutMapping("/{orderExternalId}")
    void updateOrder(@org.springframework.web.bind.annotation.RequestBody OrderDto orderDto, @PathVariable UUID orderExternalId);

    @Operation(summary = "Delete order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Order was deleted"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @DeleteMapping("/{orderExternalId}")
    void deleteOrder(@PathVariable UUID orderExternalId);

    @Operation(summary = "Get orders paged")
    @ApiResponse(responseCode = "200", description = "Orders found")
    @GetMapping
    Page<OrderDto> getOrders(@RequestParam(defaultValue = "") String searchString,
                             @PageableDefault Pageable pageable);

    @Operation(summary = "Get order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/{orderExternalId}")
    OrderDto getOrderByExternalId(@PathVariable UUID orderExternalId);
}