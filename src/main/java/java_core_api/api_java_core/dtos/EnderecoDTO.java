package java_core_api.api_java_core.dtos;

import lombok.Data;

@Data
public class EnderecoDTO {
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

    // Novos campos
    private String razaoSocial;
    private String nomeFantasia;

}
