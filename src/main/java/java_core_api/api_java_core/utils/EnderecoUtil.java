package java_core_api.api_java_core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java_core_api.api_java_core.dtos.EnderecoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class EnderecoUtil {

    private static final String API_URL = "https://publica.cnpj.ws/cnpj/";

    public static EnderecoDTO buscarEnderecoPorCnpj(String cnpj) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(API_URL + cnpj, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode est = root.path("estabelecimento");

            EnderecoDTO dto = new EnderecoDTO();
            dto.setTipoLogradouro(est.path("tipo_logradouro").asText());
            dto.setLogradouro(est.path("logradouro").asText());
            dto.setNumero(est.path("numero").asText());
            dto.setComplemento(est.path("complemento").asText(null));
            dto.setBairro(est.path("bairro").asText());
            dto.setCep(est.path("cep").asText());
            dto.setTelefone(est.path("ddd1").asText() + est.path("telefone1").asText());
            dto.setEmail(est.path("email").asText());
            dto.setCidade(est.path("cidade").path("nome").asText());
            dto.setEstado(est.path("estado").path("nome").asText());

            // Correção aqui:
            dto.setRazaoSocial(root.path("razao_social").asText());
            dto.setNomeFantasia(est.path("nome_fantasia").asText());

            return dto;
        } catch (Exception e) {
            log.error("Erro ao buscar endereço pelo CNPJ: {}", cnpj, e);
            return null;
        }
    }

}
