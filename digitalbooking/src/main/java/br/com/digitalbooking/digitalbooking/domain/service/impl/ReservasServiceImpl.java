package br.com.digitalbooking.digitalbooking.domain.service.impl;

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
    private final ProdutosService produtosService;

    public ReservasServiceImpl(ReservasRepository reservasRepository, ObjectMapper objectMapper, ProdutosRepository produtosRepository, UsuariosService usuariosService, ProdutosService produtosService) {
        this.reservasRepository = reservasRepository;
        this.objectMapper = objectMapper;
        this.produtosRepository = produtosRepository;
        this.usuariosService = usuariosService;
        this.produtosService = produtosService;
    }

    @Override
    public ReservasResponse criarReserva(ReservasResponse reservasResponse) {

        //Identificar produto, usuário, cidade, status datas inicio e final
        ProdutosResponse produto = reservasResponse.getProdutosResponse();
        UsuariosResponse usuario = reservasResponse.getUsuariosResponse();
        LocalDate dataInicio = reservasResponse.getDataInicio();
        LocalDate dataFinal = reservasResponse.getDataFinal();
       // StatusReservas status = reservasResponse.getStatus();
        //CidadesResponse cidade = reservasResponse.getCidade();

        // Verificar se existe produto

        Optional<Produtos> produtoIdentificado = produtosRepository.findById(produto.getId());

        if (produtoIdentificado.isEmpty()) {
            throw new NotFoundException(produto.getId());
        }

        //verificar se o usuario existe

        Usuarios usuarioIdentificado = usuariosService.buscarUsuarioPorId(usuario.getId());

        if (usuarioIdentificado == null) {

            throw new NotFoundException(usuario.getId());
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

        Reservas reservas = objectMapper.convertValue(reservasResponse, Reservas.class);
        Reservas registrado = reservasRepository.save(reservas);
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
