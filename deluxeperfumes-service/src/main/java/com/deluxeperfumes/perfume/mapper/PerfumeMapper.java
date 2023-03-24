package com.deluxeperfumes.perfume.mapper;

import com.deluxeperfumes.perfume.dto.PerfumeDto;
import com.deluxeperfumes.perfume.entity.Perfume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PerfumeMapper {

    @Mapping(target = "externalId", defaultExpression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDate.now())")
    Perfume toEntity(PerfumeDto perfumeDto);

    PerfumeDto toDto(Perfume perfume);

}
