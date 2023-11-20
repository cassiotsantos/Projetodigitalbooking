package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.ProdutosRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CaracteristicasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.CidadesResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.ProdutosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ProdutosListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.ProdutosWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.service.ProdutosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("v1/produtos")
@Tag(name = "Produtos" )
public class ProdutosController {

        private final ProdutosService produtosService;

        @Autowired
        public ProdutosController(ProdutosService produtosService) {
            this.produtosService = produtosService;
        }

        //Buscar por ID

        @GetMapping("{id}")
        ResponseEntity<ProdutosResponse> buscarProdutosPorId(@PathVariable UUID id) {
            Produtos produtos = produtosService.buscarProdutoPorId(id);
            ProdutosResponse response = produtosResponseByProdutos(produtos);
            return ResponseEntity.ok().body(response);
        }

        private ProdutosResponse produtosResponseByProdutos(Produtos produtos) {
            ProdutosResponse produtosResponse = new ProdutosResponse();
            produtosResponse.setId(produtos.getId());
            produtosResponse.setNome(produtos.getNome());
            produtosResponse.setDescricao(produtos.getDescricao());
            produtosResponse.setLatitude(produtos.getLatitude());
            produtosResponse.setLongitude(produtos.getLongitude());

            CidadesResponse cidades = new CidadesResponse();
            cidades.setId(produtos.getCidades().getId());
            cidades.setNome(produtos.getCidades().getNome());
            cidades.setPais(produtos.getCidades().getPais());

            CategoriasResponse categorias = new CategoriasResponse();
            categorias.setId(produtos.getCategorias().getId());
            categorias.setNome(produtos.getCategorias().getNome());
            categorias.setUrlImage(produtos.getCategorias().getUrlImage());
            categorias.setQualificacao(produtos.getCategorias().getQualificacao());

            produtosResponse.setCidades(cidades);
            produtosResponse.setCategorias(categorias);
            produtosResponse.setCaracteristicas(produtos.getProdutosCaracteristicas());

            return produtosResponse;

        }

        //Buscar todos por termo
        @GetMapping
        ResponseEntity<ProdutosWrapperResponse> buscarProdutos(@RequestParam String termo) {
            List<Produtos> produtos = produtosService.buscarProdutos(termo);
            ProdutosWrapperResponse produtosWrapperResponse = new ProdutosWrapperResponse();
            produtosWrapperResponse.setProdutos(
                    produtos.stream()
                            .map(produto -> {
                                ProdutosListResponse produtosListResponse = new ProdutosListResponse();
                                produtosListResponse.setId(produto.getId());
                                produtosListResponse.setNome(produto.getNome());
                                produtosListResponse.setDescricao(produto.getDescricao());
                                produtosListResponse.setLatitude(produto.getLatitude());
                                produtosListResponse.setLongitude(produto.getLongitude());
                                return produtosListResponse;
                            }).toList());
            return ResponseEntity.ok(produtosWrapperResponse);

        }

        //método Criar
        @PostMapping
        ResponseEntity<ProdutosResponse> criarProdutos(@RequestBody @Valid ProdutosRequest request) {

            Produtos produtos = new Produtos();
            produtos.setId(UUID.randomUUID());
            produtos.setNome(request.getNome());
            produtos.setDescricao(request.getDescricao());
            produtos.setLatitude(request.getLatitude());
            produtos.setLongitude(request.getLongitude());

            Produtos produtoCriado = produtosService.criarProduto(produtos);
            ProdutosResponse produtosResponse = produtosResponseByProdutos(produtoCriado);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosResponse);
        }

        //Método atualizar
        @PutMapping("id")
        ResponseEntity<ProdutosResponse> atualizarProduto(@PathVariable UUID id, @RequestBody @Valid ProdutosRequest request){

            Produtos produtos = produtosService.buscarProdutoPorId(id);

            produtos.setNome(request.getNome());
            produtos.setDescricao(request.getDescricao());
            produtos.setLatitude(request.getLatitude());
            produtos.setLongitude(request.getLongitude());

            Cidades cidades = new Cidades();
            cidades.setNome(request.getCidades().getNome());
            cidades.setPais(request.getCidades().getPais());

            produtos.setCidades(cidades);

            Produtos produtoAtualizado = produtosService.atualizarProduto(id, produtos);
            ProdutosResponse response = produtosResponseByProdutos(produtoAtualizado);
            return ResponseEntity.ok(response);        }

        // Método de buscar Produto por categoria
        @GetMapping("/porcategoria/{nome}")
        ResponseEntity<ProdutosWrapperResponse> listaProdutoPorCategoria(@PathVariable String nome) {

                List<Produtos> produtos = produtosService.listaProdutoPorCategoria(nome);
                ProdutosWrapperResponse produtosWrapperResponse = new ProdutosWrapperResponse();
                produtosWrapperResponse.setProdutos(
                        produtos.stream()
                                .map(produto -> {
                                    ProdutosListResponse produtosListResponse = new ProdutosListResponse();
                                    produtosListResponse.setId(produto.getId());
                                    produtosListResponse.setNome(produto.getNome());
                                    produtosListResponse.setCategorias(produto.getCategorias());
                                    produtosListResponse.setDescricao(produto.getDescricao());
                                    produtosListResponse.setLatitude(produto.getLatitude());
                                    produtosListResponse.setLongitude(produto.getLongitude());
                                    return produtosListResponse;
                                }).toList());
                return ResponseEntity.ok(produtosWrapperResponse);

        }

        // método busca produto por cidade
        @GetMapping("/porcidade/{nome}")
        ResponseEntity<ProdutosWrapperResponse> listaProdutoPorCidade(@PathVariable String nome) {
            List<Produtos> produtos = produtosService.listaProdutoPorCidade(nome);
            ProdutosWrapperResponse produtosWrapperResponse = new ProdutosWrapperResponse();
            produtosWrapperResponse.setProdutos(
                    produtos.stream()
                            .map(produto -> {
                                ProdutosListResponse produtosListResponse = new ProdutosListResponse();
                                produtosListResponse.setId(produto.getId());
                                produtosListResponse.setNome(produto.getNome());
                                produtosListResponse.setCategorias(produto.getCategorias());
                                produtosListResponse.setDescricao(produto.getDescricao());
                                produtosListResponse.setLatitude(produto.getLatitude());
                                produtosListResponse.setLongitude(produto.getLongitude());
                                return produtosListResponse;
                            }).toList());
            return ResponseEntity.ok(produtosWrapperResponse);
        }

        //Método deletar
        @DeleteMapping("{id}")
        ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
            produtosService.deletarProduto(id);
            return ResponseEntity.ok().build();
        }
    }
