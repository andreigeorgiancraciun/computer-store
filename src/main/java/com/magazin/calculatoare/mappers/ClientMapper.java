package com.magazin.calculatoare.mappers;

import com.magazin.calculatoare.dtos.ClientDTO;
import com.magazin.calculatoare.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    // Mapping from entity to DTO
    ClientDTO toDTO(Client client);

    // Mapping from DTO to entity
    Client toEntity(ClientDTO clientDTO);
}
