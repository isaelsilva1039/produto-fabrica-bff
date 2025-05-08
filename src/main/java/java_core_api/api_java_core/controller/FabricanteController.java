package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.dtos.FabricanteDTO;
import java_core_api.api_java_core.dtos.ProdutoDTO;
import java_core_api.api_java_core.services.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fabricante")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<String> salvarFabricante(@RequestBody FabricanteDTO fabricanteDTO) {
        return fabricanteService.salvarFabricante(fabricanteDTO);
    }

    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> listarProdutos() {
        return fabricanteService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarFabricante(@PathVariable("id") Long id, @RequestBody FabricanteDTO fabricanteDTO) {
        return fabricanteService.atualizarFabricante(id, fabricanteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFabricante(@PathVariable("id") Long id) {
        return fabricanteService.deletarFabricante(id);
    }
}
