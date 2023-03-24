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
}