package com.deluxeperfumes.perfume.endpoint;

import com.deluxeperfumes.perfume.dto.PerfumeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/perfume")
public interface PerfumeEndpoint {

    @Operation(summary = "Create perfume")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Create perfume")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = {
                    @Content(schema = @Schema(implementation = PerfumeDto.class))
            }
    )
    @PostMapping
    PerfumeDto createPerfume(@org.springframework.web.bind.annotation.RequestBody PerfumeDto perfumeDto);
}
