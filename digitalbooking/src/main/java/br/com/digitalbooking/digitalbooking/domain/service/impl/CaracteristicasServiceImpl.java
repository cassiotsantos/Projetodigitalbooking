package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.CaracteristicasRepository;
import br.com.digitalbooking.digitalbooking.domain.service.CaracteristicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CaracteristicasServiceImpl implements CaracteristicasService {

    private final CaracteristicasRepository caracteristicasRepository;

    @Autowired
    public CaracteristicasServiceImpl(CaracteristicasRepository caracteristicasRepository) {
        this.caracteristicasRepository = caracteristicasRepository;
    }

    @Override
    public Caracteristicas criar(Caracteristicas caracteristicas) {
        return this.caracteristicasRepository.save(caracteristicas);
    }

    @Override
    public List<Caracteristicas> buscarCaracteristicas(String termo) {

        return this.caracteristicasRepository.findByNomeStartingWith(termo);
    }

    @Override
    public Caracteristicas buscarCaracteristicasPorId(UUID id) {
        try{ return caracteristicasRepository.findById(id).orElseThrow();}
        catch (Exception e){ throw new NotFoundException(id);}
    }

    @Override
    public Caracteristicas atualizarCaracteristicas(UUID id, Caracteristicas caracteristicas) {
        try {
            caracteristicasRepository.findById(id).orElseThrow();
        } catch (Exception e) {throw new NotFoundException(id);}
        return caracteristicasRepository.save(caracteristicas);
    }


    @Override
    public void deletarCaracteristicas(UUID id) {
        try{caracteristicasRepository.findById(id).orElseThrow();
            caracteristicasRepository.deleteById(id);}
        catch(Exception e) {throw new NotFoundException(id);};

    }
}
