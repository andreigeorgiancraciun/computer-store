package com.magazin.calculatoare.mappers;

import com.magazin.calculatoare.dtos.VanzareDTO;
import com.magazin.calculatoare.entities.Vanzare;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VanzareMapper {
    VanzareMapper INSTANCE = Mappers.getMapper(VanzareMapper.class);

    // Mapping from entity to DTO
    VanzareDTO toDTO(Vanzare vanzare);

    // Mapping from DTO to entity
    Vanzare toEntity(VanzareDTO vanzareDTO);

}
