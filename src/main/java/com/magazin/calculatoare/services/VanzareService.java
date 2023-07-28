package com.magazin.calculatoare.services;

import com.magazin.calculatoare.dtos.VanzareDTO;
import com.magazin.calculatoare.entities.Vanzare;
import com.magazin.calculatoare.mappers.VanzareMapper;
import com.magazin.calculatoare.repositories.VanzareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VanzareService {
    private final VanzareRepository vanzareRepository;

    @Autowired
    public VanzareService(VanzareRepository vanzareRepository) {
        this.vanzareRepository = vanzareRepository;
    }

    public List<VanzareDTO> getAllVanzari() {
        List<Vanzare> vanzareList = vanzareRepository.findAll();

        return vanzareList.stream()
                .map(VanzareMapper.INSTANCE::toDTO)
                .toList();
    }

    public VanzareDTO getVanzareById(Long id) {
        Vanzare vanzare = vanzareRepository.findById(id).orElse(null);
        return VanzareMapper.INSTANCE.toDTO(vanzare);
    }

    public VanzareDTO saveVanzare(VanzareDTO vanzareDTO) {
        Vanzare vanzare = VanzareMapper.INSTANCE.toEntity(vanzareDTO);
        return VanzareMapper.INSTANCE.toDTO(vanzareRepository.save(vanzare));
    }

    public void deleteVanzareById(Long id) {
        vanzareRepository.deleteById(id);
    }
}

