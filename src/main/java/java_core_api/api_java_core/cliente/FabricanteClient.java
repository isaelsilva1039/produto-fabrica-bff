package java_core_api.api_java_core.cliente;

import java_core_api.api_java_core.dtos.FabricanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FabricanteClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://host.docker.internal:8085/api/v1/fabricante";

    public ResponseEntity<String> salvarFabricante(FabricanteDTO fabricante) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FabricanteDTO> entity = new HttpEntity<>(fabricante, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> atualizarFabricante(Long id, FabricanteDTO fabricante) {
        String urlWithId = url + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FabricanteDTO> entity = new HttpEntity<>(fabricante, headers);
        return restTemplate.exchange(urlWithId, HttpMethod.PUT, entity, String.class);
    }

    public ResponseEntity<Void> deletarFabricante(Long id) {
        String urlWithId = url + "/" + id;
        return restTemplate.exchange(urlWithId, HttpMethod.DELETE, null, Void.class);
    }

    public List<FabricanteDTO> listarFabricantes() {

        ResponseEntity<List<FabricanteDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FabricanteDTO>>() {}
        );

        return response.getBody();
    }

}
