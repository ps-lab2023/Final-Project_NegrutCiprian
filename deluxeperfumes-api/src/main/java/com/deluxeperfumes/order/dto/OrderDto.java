package com.deluxeperfumes.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(requiredProperties = {"username", "perfumeNames"})
public class OrderDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID externalId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String identifier;

    private String username;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String orderCreatedMessage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double orderPrice;

    private List<String> perfumeNames = new ArrayList<>();
}
