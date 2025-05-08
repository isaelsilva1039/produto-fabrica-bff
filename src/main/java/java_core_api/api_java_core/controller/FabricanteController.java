package java_core_api.api_java_core.controller;

import jakarta.validation.Valid;
import java_core_api.api_java_core.dtos.FabricanteDTO;
import java_core_api.api_java_core.domain.Fabricante;
import java_core_api.api_java_core.services.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fabricante")
@Validated
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody FabricanteDTO fabricanteDTO) {
        try {
            Fabricante fabricante = new Fabricante();
            fabricante.setNome(fabricanteDTO.getNome());
            fabricante.setCnpj(fabricanteDTO.getCnpj());


            fabricanteService.salvar(fabricante);

            return ResponseEntity.status(HttpStatus.CREATED).body("Fabricante salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar fabricante." + e);
        }
    }


    // Endpoint para listar todos os Fabricantes
    @GetMapping
    public ResponseEntity<List<Fabricante>> listar() {
        try {
            List<Fabricante> fabricantes = fabricanteService.listarTodos();
            return ResponseEntity.ok(fabricantes);  // Retorna a lista de fabricantes
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // Em caso de erro, retorna 500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> buscarPorId(@PathVariable Long id) {
        try {
            Fabricante fabricante = fabricanteService.buscarPorId(id);
            if (fabricante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Caso não encontre
            }
            return ResponseEntity.ok(fabricante);  // Retorna o fabricante encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @Valid @RequestBody FabricanteDTO fabricanteDTO) {
        try {

            Fabricante FabricanteExiste = fabricanteService.buscarPorId(id);

            if (FabricanteExiste == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Fabricante fabricante = new Fabricante();
            fabricante.setId(id);
            fabricante.setNome(fabricanteDTO.getNome());
            fabricante.setCnpj(fabricanteDTO.getCnpj());

            fabricanteService.atualizar(fabricante);

            return ResponseEntity.status(HttpStatus.OK).body("Fabricante atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar fabricante: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {

            Fabricante FabricanteExiste = fabricanteService.buscarPorId(id);

            if (FabricanteExiste == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            fabricanteService.deletar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante deletado com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar fabricante: " + e.getMessage());

        }
    }


}
