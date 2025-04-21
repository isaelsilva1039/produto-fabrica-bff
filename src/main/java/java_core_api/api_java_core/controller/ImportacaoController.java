package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.dtos.CupomImportacaoDTO;
import java_core_api.api_java_core.dtos.NotaFiscalImportacaoDTO;
import java_core_api.api_java_core.services.CupomImportacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/importacao")
public class ImportacaoController {

    @Autowired
    private CupomImportacaoService importacaoService;

    @PostMapping("/nota-fiscal")
    public ResponseEntity<Void> importarNota(@RequestBody CupomImportacaoDTO notaFiscal) {
        importacaoService.processar(notaFiscal);
        return ResponseEntity.ok().build();
    }
}

