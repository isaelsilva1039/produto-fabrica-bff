package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private Long id;
    private String descricao;
    private Categoria categoria;
}
