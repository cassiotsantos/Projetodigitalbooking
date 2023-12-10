package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.api.dto.request.ReservasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CidadesResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.ProdutosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.ReservasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.UsuariosResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.entity.StatusReservas;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service

public class ReservasServiceImpl implements ReservasService {

    private final ReservasRepository reservasRepository;
    private final ObjectMapper objectMapper;
    private final ProdutosRepository produtosRepository;
    private final UsuariosService usuariosService;

    private final UsuariosRepository usuariosRepository;
    private final ProdutosService produtosService;

    public ReservasServiceImpl(ReservasRepository reservasRepository, ObjectMapper objectMapper, ProdutosRepository produtosRepository, UsuariosService usuariosService, UsuariosRepository usuariosRepository, ProdutosService produtosService) {
        this.reservasRepository = reservasRepository;
        this.objectMapper = objectMapper;
        this.produtosRepository = produtosRepository;
        this.usuariosService = usuariosService;
        this.usuariosRepository = usuariosRepository;
        this.produtosService = produtosService;
    }

    @Override
    public ReservasResponse criarReserva(Reservas request, UUID usuarioId, UUID produtos) {

        //Identificar produto, usuário, cidade, status datas inicio e final
        LocalDate dataInicio = request.getDataInicio();
        LocalDate dataFinal = request.getDataFinal();
       // StatusReservas status = reservasResponse.getStatus();
        //CidadesResponse cidade = reservasResponse.getCidade();

        // Verificar se existe produto

        Optional<Produtos> produtoIdentificado = produtosRepository.findById(produtos);

        if (produtoIdentificado.isEmpty()) {
            throw new NotFoundException(produtos);
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

         Produtos produtoIdentificar = produtosRepository.findById(produtos).orElseThrow();
         request.setProdutos(produtoIdentificar);

         Usuarios usuarioIdentificar = usuariosRepository.findUsuarioById(usuarioId).orElseThrow();
         request.setUsuarioId(usuarioIdentificar);
         Reservas registrado = reservasRepository.save(request);
         ReservasResponse reservasResponseRegistrado = objectMapper.convertValue(registrado, ReservasResponse.class);

        return reservasResponseRegistrado;
    }

    @Override
    public List<Reservas> findByProdutosId(UUID produtoId) {
              return reservasRepository.findByProdutosId(produtoId);}

    @Override
    public List<Reservas> findByUsuarioId(Usuarios usuarioId) {
              return reservasRepository.findByUsuarioId(usuarioId);}

}
