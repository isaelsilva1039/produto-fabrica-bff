package java_core_api.api_java_core.services;

import java_core_api.api_java_core.cliente.ProdutoClient;
import java_core_api.api_java_core.dtos.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoClient produtoClient;

    public ResponseEntity<String> salvarProduto(ProdutoDTO produtoDTO) {
        try {
            produtoClient.salvarProduto(produtoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public ResponseEntity<String> atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        try {
            produtoClient.atualizarProduto(id, produtoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        try {
            List<ProdutoDTO> produtos = produtoClient.listarProdutos();
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> deletarProduto(Long id) {
        try {
            produtoClient.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
