package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class Mercado {
    private Long id;
    private String fantasia;
    private String razaoSocial;
    private String tipoPessoa;
    private Grupo grupo;
    private Bandeira bandeira;
    private Integer idCidade;
    private String bairro;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String ddd;
    private String telefone;
    private String cnpj;
    private Endereco endereco;
}
