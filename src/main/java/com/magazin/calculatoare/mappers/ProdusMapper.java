package com.magazin.calculatoare.mappers;

import com.magazin.calculatoare.dtos.ProdusDTO;
import com.magazin.calculatoare.entities.Produs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdusMapper {
    ProdusMapper INSTANCE = Mappers.getMapper(ProdusMapper.class);

    // Mapping from entity to DTO
    ProdusDTO toDTO(Produs produs);

    // Mapping from DTO to entity
    Produs toEntity(ProdusDTO produsDTO);
}
