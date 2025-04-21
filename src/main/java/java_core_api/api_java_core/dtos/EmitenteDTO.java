package java_core_api.api_java_core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// dto/EmitenteDTO.java
@Getter
@Setter
@AllArgsConstructor
public class EmitenteDTO {
    private String chaveAcesso;
    private String cnpj;
    private String razaoSocial;
    private String endereco;
    private String nomeFantasia;

    // getters e setters
}
