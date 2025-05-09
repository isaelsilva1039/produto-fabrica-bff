package java_core_api.api_java_core.cliente;

import java_core_api.api_java_core.dtos.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProdutoClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://host.docker.internal:8087/api/v1/produto";

    public ResponseEntity<String> salvarProduto(ProdutoDTO produto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProdutoDTO> entity = new HttpEntity<>(produto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> atualizarProduto(Long id, ProdutoDTO produto) {
        String urlWithId = url + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProdutoDTO> entity = new HttpEntity<>(produto, headers);
        return restTemplate.exchange(urlWithId, HttpMethod.PUT, entity, String.class);
    }

    public ResponseEntity<Void> deletarProduto(Long id) {
        String urlWithId = url + "/" + id;
        return restTemplate.exchange(urlWithId, HttpMethod.DELETE, null, Void.class);
    }

    public List<ProdutoDTO> listarProdutos() {
        return restTemplate.exchange(url, HttpMethod.GET, null, List.class).getBody();
    }
}
