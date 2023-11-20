package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.CidadesRepository;
import br.com.digitalbooking.digitalbooking.domain.service.CidadesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CidadesServiceImpl implements CidadesService {
    private final CidadesRepository cidadesRepository;

    @Autowired
    public CidadesServiceImpl(CidadesRepository cidadesRepository) {
        this.cidadesRepository = cidadesRepository;
    }

    @Override
    public Cidades criarCidades(Cidades cidades) {
        return cidadesRepository.save(cidades);
    }
   @Override
   public List<Cidades> buscarCidades(String termo) {
        return cidadesRepository.findByNomeStartingWith(termo);
    }
    @Override
    public Cidades buscarCidadePorId(UUID id) {
        try{ return cidadesRepository.findById(id).orElseThrow();}
        catch (Exception e){ throw new NotFoundException(id);}
    }

}
