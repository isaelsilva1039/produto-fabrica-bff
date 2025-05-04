package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.dtos.ColetaNotaDTO;
import java_core_api.api_java_core.dtos.PrecoItemMercadoDTO;
import java_core_api.api_java_core.mapper.UsuarioMapper;
import java_core_api.api_java_core.services.NotaFiscalService;
import java_core_api.api_java_core.services.PrecoItemMercadoService;
import java_core_api.api_java_core.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@Slf4j
public class ItemMercadosController {

    @Autowired
    private PrecoItemMercadoService precoItemMercadoService;

    @GetMapping("/lista")
    public ResponseEntity<?> listarItemPorMercado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) Double raioKm
    ) {
        try {
            List<PrecoItemMercadoDTO> itens = precoItemMercadoService.buscarPaginado(page, size, latitude, longitude, raioKm);
            return ResponseEntity.ok(itens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar itens: " + e.getMessage());
        }
    }



    @GetMapping("/lista/cidade")
    public ResponseEntity<?> listarPorProdutoPorCidade(
            @RequestParam(required = true) String cidade
    ) {
        try {

            List<PrecoItemMercadoDTO> precos = precoItemMercadoService.buscarPorProdutosPorCidade(cidade);
            return ResponseEntity.ok(precos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar preços do produto por cidade: " + e.getMessage());
        }
    }

    @GetMapping("/por-produto/{idProdutoItem}")
    public ResponseEntity<?> listarPorProdutoOrdenadoPorPreco(
            @PathVariable Long idProdutoItem,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) Double raioKm
    ) {
        try {
            List<PrecoItemMercadoDTO> precos = precoItemMercadoService.buscarPorProdutoOrdenadoPorPreco(idProdutoItem, latitude, longitude, raioKm);
            return ResponseEntity.ok(precos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar preços do produto: " + e.getMessage());
        }
    }




}
