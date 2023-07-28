package com.magazin.calculatoare.mappers;

import com.magazin.calculatoare.dtos.AngajatDTO;
import com.magazin.calculatoare.entities.Angajat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AngajatMapper {
    AngajatMapper INSTANCE = Mappers.getMapper(AngajatMapper.class);

    // Mapping from entity to DTO
    AngajatDTO toDTO(Angajat angajat);

    // Mapping from DTO to entity
    Angajat toEntity(AngajatDTO angajatDTO);
}
