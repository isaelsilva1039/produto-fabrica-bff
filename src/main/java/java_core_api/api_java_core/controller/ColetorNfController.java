package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.domain.NotaFiscal;
import java_core_api.api_java_core.domain.Usuario;
import java_core_api.api_java_core.dtos.ColetaNotaDTO;
import java_core_api.api_java_core.dtos.LoginResponseDTO;
import java_core_api.api_java_core.exception.CredenciaisInvalidasException;
import java_core_api.api_java_core.exception.EmailJaExisteException;
import java_core_api.api_java_core.mapper.UsuarioMapper;
import java_core_api.api_java_core.services.NotaFiscalService;
import java_core_api.api_java_core.services.UsuarioService;
import java_core_api.api_java_core.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/nf")
@Slf4j
public class ColetorNfController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping("/coletar")
    public ResponseEntity<?> coletar(@RequestBody ColetaNotaDTO dto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long userId = (Long) authentication.getPrincipal();

            notaFiscalService.coletarNota(dto, userId);
            return ResponseEntity.ok("Nota coletada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao coletar nota." + e.getMessage());
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarNotasDoUsuario(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        List<NotaFiscal> notas = notaFiscalService.buscarNotasPorUsuario(userId);
        return ResponseEntity.ok(notas);
    }


    @GetMapping("/listar/pendente")
    public ResponseEntity<?> listarNotasPendete() {
        log.info("🔍 Iniciando listagem de notas fiscais pendentes [][] ✅ {},");

        List<NotaFiscal> notas = notaFiscalService.buscarNotasPendentes();

        log.info("✅ {} notas fiscais pendentes encontradas", notas.size());
        return ResponseEntity.ok(notas);
    }


}
