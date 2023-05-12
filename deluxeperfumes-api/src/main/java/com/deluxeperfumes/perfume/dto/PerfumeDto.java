package com.deluxeperfumes.perfume.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(requiredProperties = {"name", "identifier", "category", "description"})
public class PerfumeDto {

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String identifier;

    private String category;

    private String description;

    private double price;

    @JsonProperty(access = Access.READ_ONLY)
    private UUID externalId;

    private String promo;

    private boolean outOfStock;
}
