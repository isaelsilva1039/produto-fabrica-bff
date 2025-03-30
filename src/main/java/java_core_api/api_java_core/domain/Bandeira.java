package java_core_api.api_java_core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bandeira {
    private Long id;
    private String descricao;
    private String hashImagem;
    private String caminhoImagem;
}
