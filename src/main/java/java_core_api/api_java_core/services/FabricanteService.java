package java_core_api.api_java_core.services;

import java_core_api.api_java_core.cliente.FabricanteClient;
import java_core_api.api_java_core.dtos.FabricanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteClient fabricanteClient;

    public ResponseEntity<String> salvarFabricante(FabricanteDTO fabricanteDTO) {
        try {
            fabricanteClient.salvarFabricante(fabricanteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fabricante salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar fabricante: " + e.getMessage());
        }
    }

    public ResponseEntity<String> atualizarFabricante(Long id, FabricanteDTO fabricanteDTO) {
        try {
            fabricanteClient.atualizarFabricante(id, fabricanteDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar fabricante: " + e.getMessage());
        }
    }

    public ResponseEntity<String> deletarFabricante(Long id) {
        try {
            fabricanteClient.deletarFabricante(id);
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar fabricante: " + e.getMessage());
        }
    }

    public ResponseEntity<List<FabricanteDTO>> listarTodos() {
        try {
            List<FabricanteDTO> fabricantes = fabricanteClient.listarFabricantes();
            return ResponseEntity.status(HttpStatus.OK).body(fabricantes);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
