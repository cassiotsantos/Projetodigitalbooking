package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.repository.CidadesRepository;
import br.com.digitalbooking.digitalbooking.domain.service.CidadesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CidadesServiceImpl implements CidadesService {
    private final CidadesRepository cidadesRepository;
    @Override
    public Cidades criarCidades(Cidades cidades) {
        return cidadesRepository.save(cidades);
    }
    @Override
    public List<Cidades> buscarCidades(String termo) {
        return cidadesRepository.findByNomeStartingWith(termo);
    }
}
