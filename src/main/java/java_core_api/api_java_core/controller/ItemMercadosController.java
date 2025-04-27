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
    public ResponseEntity<?> listarItemPorMercado() {
        try {
            List<PrecoItemMercadoDTO> itens = precoItemMercadoService.buscarTodos();
            return ResponseEntity.ok(itens);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar itens : " + e);
        }

    }





}
