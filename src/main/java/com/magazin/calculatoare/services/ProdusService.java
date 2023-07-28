package com.magazin.calculatoare.services;

import com.magazin.calculatoare.dtos.ProdusDTO;
import com.magazin.calculatoare.entities.Produs;
import com.magazin.calculatoare.mappers.ProdusMapper;
import com.magazin.calculatoare.repositories.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdusService {
    private final ProdusRepository produsRepository;

    @Autowired
    public ProdusService(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }

    public List<ProdusDTO> getAllProducts() {
        List<Produs> produsList = produsRepository.findAll();

        return produsList.stream()
                .map(ProdusMapper.INSTANCE::toDTO)
                .toList();
    }

    public ProdusDTO getProductById(Long id) {
        Produs produs = produsRepository.findById(id).orElse(null);
        return ProdusMapper.INSTANCE.toDTO(produs);
    }

    public ProdusDTO saveProduct(ProdusDTO produsDTO) {
        Produs produs = ProdusMapper.INSTANCE.toEntity(produsDTO);
        return ProdusMapper.INSTANCE.toDTO(produsRepository.save(produs));
    }

    public void deleteProductById(Long id) {
        produsRepository.deleteById(id);
    }
}

