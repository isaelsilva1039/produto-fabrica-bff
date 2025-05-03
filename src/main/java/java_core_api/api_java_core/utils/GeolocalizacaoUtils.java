package java_core_api.api_java_core.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java_core_api.api_java_core.dtos.CoordenadasDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class GeolocalizacaoUtils {

    private static final String RECEITAWS_API_URL = "https://www.receitaws.com.br/v1/cnpj/";
    private static final String OSM_API_URL = "https://nominatim.openstreetmap.org/search";

    public CoordenadasDTO obterCoordenadasPorCNPJ(String cnpj) {
        // Criando a instância do HttpClient diretamente
        HttpClient httpClient = HttpClients.createDefault();

        // Passo 1: Obter o endereço completo da ReceitaWS usando o CNPJ
        String urlReceita = RECEITAWS_API_URL + cnpj;
        ReceitaWSResponse receitaWSResponse = fazerRequisicaoGET(urlReceita, ReceitaWSResponse.class);

        // Verificando se obtemos o endereço da ReceitaWS
        if (receitaWSResponse == null || receitaWSResponse.getLogradouro() == null) {
            System.out.println("Endereço não encontrado para o CNPJ: " + cnpj);
            return null;
        }

        // Montando o endereço completo
        String enderecoCompleto = receitaWSResponse.getLogradouro() + ", " + receitaWSResponse.getNumero() + " "
                + receitaWSResponse.getBairro() + ", " + receitaWSResponse.getMunicipio() + ", " + receitaWSResponse.getUf();

        // Passo 2: Obter as coordenadas através da API OSM
        String urlOSM = UriComponentsBuilder.fromHttpUrl(OSM_API_URL)
                .queryParam("q", enderecoCompleto)
                .queryParam("format", "json")
                .toUriString();

        // Exibindo a URL para depuração
        System.out.println("URL da requisição OSM: " + urlOSM);

        // Realizando a consulta na API OSM
        List<Map<String, Object>> response = fazerRequisicaoGET(urlOSM, List.class);

        // Verificando o tipo da resposta
        System.out.println("Tipo de resposta: " + (response != null ? response.getClass().getName() : "null"));

        // Verificando o conteúdo da resposta
        System.out.println("Conteúdo da resposta: " + response);

        // Verificando se a resposta é nula ou vazia
        if (response == null || response.isEmpty()) {
            System.out.println("Resposta da API OSM vazia ou nula");
            return null;
        }

        // Pegando a primeira resposta (podemos aplicar lógica para escolher a melhor)
        Map<String, Object> melhorResposta = response.get(0);

        // Extraindo as coordenadas
        String lat = (String) melhorResposta.get("lat");
        String lon = (String) melhorResposta.get("lon");

        // Exibindo as coordenadas
        System.out.println("Latitude: " + lat + ", Longitude: " + lon);

        BigDecimal latitude = new BigDecimal(lat);
        BigDecimal longitude = new BigDecimal(lon);

        // Retornando o DTO com as coordenadas
        return new CoordenadasDTO(latitude, longitude);
    }

    // Método que realiza requisição GET e retorna o objeto do tipo T
    private <T> T fazerRequisicaoGET(String url, Class<T> responseType) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "application/json");

            HttpResponse response = client.execute(httpGet);
            String jsonResponse = EntityUtils.toString(response.getEntity());

            // Utilizando a biblioteca Jackson (ou Gson) para deserializar a resposta JSON
            T result = new ObjectMapper().readValue(jsonResponse, responseType);
            return result;

        } catch (IOException e) {
            System.out.println("Erro ao fazer requisição GET para " + url + ": " + e.getMessage());
            return null;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReceitaWSResponse {
        private String logradouro;
        private String numero;
        private String bairro;
        private String municipio;
        private String uf;

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }
}
