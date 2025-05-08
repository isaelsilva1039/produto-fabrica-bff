package java_core_api.api_java_core.controller;

import jakarta.validation.Valid;
import java_core_api.api_java_core.dtos.ProdutoDTO; // Alterar para ProdutoDTO
import java_core_api.api_java_core.domain.Produto;
import java_core_api.api_java_core.services.ProdutoService; // Alterar para ProdutoService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Endpoint para salvar um Produto
    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ProdutoDTO produtoDTO) {
        try {
            Produto produto = new Produto();
            produto.setNome(produtoDTO.getNome());
            produto.setCodigoBarras(produtoDTO.getCodigoBarras());
            produto.setFabricanteId(produtoDTO.getFabricanteId());

            produtoService.salvar(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Produto salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar produto." + e);
        }
    }

    // Endpoint para listar todos os Produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        try {
            List<Produto> produtos = produtoService.listarTodos();


            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para buscar Produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            if (produto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
            }
            return ResponseEntity.ok(produto);  // Retorna o produto encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para atualizar Produto
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        try {

            Produto produtoExistente = produtoService.buscarPorId(id);

            if (produtoExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
            }

            Produto produto = new Produto();
            produto.setId(id);
            produto.setNome(produtoDTO.getNome());
            produto.setCodigoBarras(produtoDTO.getCodigoBarras());
            produto.setFabricanteId(produtoDTO.getFabricanteId());

            produtoService.atualizar(produto);

            return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // Endpoint para deletar Produto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            Produto produtoExistente = produtoService.buscarPorId(id);

            if (produtoExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
            }

            produtoService.deletar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
