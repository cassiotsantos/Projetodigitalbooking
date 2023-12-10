package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;
import br.com.digitalbooking.digitalbooking.domain.exception.DatasIncorrectException;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.ProdutosRepository;
import br.com.digitalbooking.digitalbooking.domain.repository.ReservasRepository;
import br.com.digitalbooking.digitalbooking.domain.repository.UsuariosRepository;
import br.com.digitalbooking.digitalbooking.domain.service.ProdutosService;
import br.com.digitalbooking.digitalbooking.domain.service.ReservasService;
import br.com.digitalbooking.digitalbooking.domain.service.UsuariosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ReservasServiceImpl implements ReservasService {

    private final ReservasRepository reservasRepository;
    private final ObjectMapper objectMapper;
    private final ProdutosRepository produtosRepository;
    private final UsuariosService usuariosService;

    private final UsuariosRepository usuariosRepository;
    private final ProdutosService produtosService;

    @Override
    @Transactional
    public Reservas criarReserva(Reservas request, UUID usuarioId, UUID produtoId) {

        //Identificar datas
        LocalDate dataInicio = request.getDataInicio();
        LocalDate dataFinal = request.getDataFinal();

        // Verificar se existe produto
        Optional<Produtos> produtoIdentificado = produtosRepository.findById(produtoId);

        if (produtoIdentificado.isEmpty()) {
            throw new NotFoundException(produtoId);
        }

        //verificar se o usuario existe

        Usuarios usuarioIdentificado = usuariosService.buscarUsuarioPorId(usuarioId);

        if (usuarioIdentificado == null) {

            throw new NotFoundException(usuarioId);
        }

        // verificar se existem datas
        if (dataInicio == null){
            throw  new DatasIncorrectException("Data inicio invalida");
        }
        if (dataFinal == null){
            throw new DatasIncorrectException("Data final invalida");
        }

        // Verificar se a data não é anterior a data de hoje
         if (dataInicio.isBefore(LocalDate.now()) && dataInicio.isEqual(LocalDate.now())){
            throw new DatasIncorrectException("Data da reserva não pode ser anterior a data de hoje ou para o mesmo dia");
         }

         Usuarios usuarioIdentificar = usuariosRepository.findById(usuarioId).orElseThrow(() -> new NotFoundException(usuarioId));
         request.setUsuarioId(usuarioIdentificar);

         Produtos produtoIdentificar = produtosRepository.findById(produtoId).orElseThrow(() -> new NotFoundException(produtoId));
         request.setProdutos(produtoIdentificar);

        return reservasRepository.save(request);
    }

    @Override
    public List<Reservas> findByProdutosId(UUID produtoId) {
              return reservasRepository.findByProdutosId(produtoId);}

    @Override
    public List<Reservas> findByUsuarioId(Usuarios usuarioId) {
              return reservasRepository.findByUsuarioId(usuarioId);}

}
