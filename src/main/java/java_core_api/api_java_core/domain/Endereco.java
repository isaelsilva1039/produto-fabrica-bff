package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Endereco {
    private Long id;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String telefone;
    private String email;
    private String cidade;
    private String estado;

    // Getters and Setters
}
