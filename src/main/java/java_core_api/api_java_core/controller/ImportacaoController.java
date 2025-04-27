package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.dtos.CupomImportacaoDTO;
import java_core_api.api_java_core.dtos.NotaFiscalImportacaoDTO;
import java_core_api.api_java_core.services.CupomImportacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/importacao")
public class ImportacaoController {

    private static final Logger logger = LoggerFactory.getLogger(ImportacaoController.class);


    @Autowired
    private CupomImportacaoService importacaoService;

    @PostMapping("/nota-fiscal")
    public ResponseEntity<?> importarNota(@RequestBody CupomImportacaoDTO notaFiscal) {
        try {
            logger.info("[INFO] Iniciando processamento da nota fiscal...");
            importacaoService.processar(notaFiscal);
            logger.info("[INFO] Nota fiscal importada com sucesso.");
            return ResponseEntity.ok("Nota fiscal importada com sucesso.");
        } catch (Exception e) {
            logger.error("[ERROR] Erro ao importar nota fiscal", e); // loga o erro COMPLETO (stacktrace)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao importar nota fiscal: " + (e.getMessage() != null ? e.getMessage() : e.toString()));
        }
    }

}

