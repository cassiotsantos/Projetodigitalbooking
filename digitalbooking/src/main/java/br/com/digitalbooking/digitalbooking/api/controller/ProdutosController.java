package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.ProdutosRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.ProdutosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ProdutosListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.ProdutosWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.service.ProdutosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        ResponseEntity<UUID> criarProdutos(@RequestBody @Valid ProdutosRequest request) {

            Produtos produtos = new Produtos();
            produtos.setId(UUID.randomUUID());
            produtos.setNome(request.getNome());
            produtos.setDescricao(request.getDescricao());
            produtos.setLatitude(request.getLatitude());
            produtos.setLongitude(request.getLongitude());

            Produtos produtoCriado = produtosService.criarProduto(produtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado.getId());
        }

        //Método atualizar
        @PutMapping("id")
        ResponseEntity<?>atualizarProduto(@PathVariable UUID id, @RequestBody @Valid ProdutosRequest request){

            Produtos produtos = produtosService.buscarProdutoPorId(id);
            produtos.setNome(request.getNome());
            produtos.setDescricao(request.getDescricao());
            produtos.setLatitude(request.getLatitude());
            produtos.setLongitude(request.getLongitude());

            Produtos atualizarProduto = produtosService.atualizarProduto(id,produtos);
            return ResponseEntity.ok(atualizarProduto);        }

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


        //Método deletar
        @DeleteMapping("{id}")
        ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
            produtosService.deletarProduto(id);
            return ResponseEntity.ok().build();
        }
    }
