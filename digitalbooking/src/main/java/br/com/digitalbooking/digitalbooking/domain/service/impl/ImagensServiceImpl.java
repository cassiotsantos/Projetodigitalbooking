package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.ImagensRepository;
import br.com.digitalbooking.digitalbooking.domain.service.ImagensService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class ImagensServiceImpl implements ImagensService {
    private final ImagensRepository imagensRepository;
    @Override
    public Imagens criarImagem(Imagens imagens) {
        return imagensRepository.save(imagens);
    }
    @Override
    public Imagens buscarImagensPorId(UUID id) {
        try{ return imagensRepository.findById(id).orElseThrow();}
        catch (Exception e){ throw new NotFoundException(id);}
    }
}
