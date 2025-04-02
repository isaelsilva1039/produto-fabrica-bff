package java_core_api.api_java_core.controller;

import jakarta.servlet.http.HttpServletRequest;
import java_core_api.api_java_core.domain.Bandeira;
import java_core_api.api_java_core.services.BandeiraService;
import java_core_api.api_java_core.utils.BaseUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bandeiras")
public class BandeiraController {

    @Autowired
    private BandeiraService bandeiraService;

    @PostMapping
    public ResponseEntity<Bandeira> salvar(
            @RequestParam String descricao,
            @RequestParam MultipartFile imagem,
            HttpServletRequest request
    ) throws Exception {
        Bandeira bandeira = bandeiraService.salvar(descricao, imagem);
        String baseUrl = BaseUrl.getBaseUrl(request);
        bandeira.setCaminhoImagem(baseUrl + "/api/v1/bandeiras/imagens/" + bandeira.getHashImagem());
        return ResponseEntity.ok(bandeira);
    }

    @GetMapping
    public ResponseEntity<List<Bandeira>> listar(HttpServletRequest request) {
        return ResponseEntity.ok(bandeiraService.listarTodas(request));
    }


    @GetMapping("/imagens/{hash}")
    public ResponseEntity<byte[]> visualizarImagem(@PathVariable String hash) throws IOException {
        File file = new File("arquivos/bandeiras", hash);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] imagem = Files.readAllBytes(file.toPath());
        String contentType = Files.probeContentType(file.toPath());

        // fallback seguro
        if (contentType == null || !contentType.startsWith("image/")) {
            contentType = "image/png";
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + hash + "\"")
                .body(imagem);
    }

}
