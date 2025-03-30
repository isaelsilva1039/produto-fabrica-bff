package java_core_api.api_java_core.services;

import jakarta.servlet.http.HttpServletRequest;
import java_core_api.api_java_core.domain.Bandeira;
import java_core_api.api_java_core.mapper.BandeiraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandeiraService {

    @Autowired
    private BandeiraMapper bandeiraMapper;

    public Bandeira salvar(String descricao, MultipartFile imagem) throws Exception {

        String pastaDestino = "arquivos/bandeiras";
        File pasta = new File(pastaDestino);
        if (!pasta.exists()) pasta.mkdirs();

        // Gerar hash seguro da imagem
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(imagem.getBytes());
        String hash = Base64.getEncoder().encodeToString(hashBytes)
                .replaceAll("[/=+]", ""); // remove caracteres problemáticos para nome de arquivo/URL

        // Salvar a imagem no disco usando o hash como nome do arquivo
        File destino = new File(pastaDestino, hash);
        if (!destino.exists()) {
            try (FileOutputStream fos = new FileOutputStream(destino)) {
                fos.write(imagem.getBytes());
            }
        }

        Bandeira bandeira = new Bandeira();
        bandeira.setDescricao(descricao);
        bandeira.setHashImagem(hash);
        bandeira.setCaminhoImagem("/api/v1/bandeiras/imagens/" + hash); // URL para visualizar

        bandeiraMapper.salvar(bandeira);
        return bandeira;
    }

    public List<Bandeira> listarTodas(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return bandeiraMapper.listarTodas().stream().map(b -> {
            Bandeira bandeira = new Bandeira();
            bandeira.setId(b.getId());
            bandeira.setDescricao(b.getDescricao());
            bandeira.setHashImagem(b.getHashImagem());
            bandeira.setCaminhoImagem(baseUrl + "/api/v1/bandeiras/imagens/" + b.getHashImagem());
            return bandeira;
        }).collect(Collectors.toList());
    }

}
