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

    @Operation(summary = "Update perfume")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Perfume was updated")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = {
                    @Content(schema = @Schema(implementation = PerfumeDto.class))
            }
    )
    @PutMapping("/{perfumeExternalId}")
    void updatePerfume(@org.springframework.web.bind.annotation.RequestBody PerfumeDto perfumeDto, @PathVariable UUID perfumeExternalId);

    @Operation(summary = "Delete perfume")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Perfume was deleted"),
            @ApiResponse(responseCode = "404", description = "Perfume not found", content = @Content)})
    @DeleteMapping("/{perfumeExternalId}")
    void deletePerfume(@PathVariable UUID perfumeExternalId);

    @Operation(summary = "Get perfumes paged")
    @ApiResponse(responseCode = "200", description = "Perfumes found")
    @GetMapping
    Page<PerfumeDto> getPerfumes(@RequestParam(defaultValue = "") String searchString,
                                 @PageableDefault Pageable pageable);

    @Operation(summary = "Get perfume")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Perfume found"),
            @ApiResponse(responseCode = "404", description = "Perfume not found", content = @Content)})
    @GetMapping("/{perfumeExternalId}")
    PerfumeDto getPerfumeByExternalId(@PathVariable UUID perfumeExternalId);
}
