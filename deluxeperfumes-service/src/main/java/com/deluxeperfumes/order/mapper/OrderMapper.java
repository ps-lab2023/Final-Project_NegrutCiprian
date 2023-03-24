package com.deluxeperfumes.order.mapper;


import com.deluxeperfumes.order.dto.OrderDto;
import com.deluxeperfumes.order.entity.Order;
import com.deluxeperfumes.perfume.entity.Perfume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OrderMapper {

    @Mapping(target = "externalId", defaultExpression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDate.now())")
    Order toEntity(OrderDto orderDto);

    @Named("convertPerfumesToString")
    static List<String> convertPerfumesToString(List<Perfume> list){
        ArrayList<String> listForReturn = new ArrayList<>();
        list.forEach(elem -> {
            listForReturn.add(elem.getName());
        });
        return listForReturn;
    }

    @Mapping(source = "order.perfumes", target = "perfumeNames", qualifiedByName = "convertPerfumesToString")
    OrderDto toDto(Order order);

}
