package java_core_api.api_java_core.dtos;

import lombok.Getter;
import lombok.Setter;

// dto/ProdutoImportadoDTO.java
@Getter
@Setter
public class ProdutoImportadoDTO {
    private String descricao;
    private String codigoInterno;
    private String ncm;
    private String codigoBarras;
    private String qtde;
    private String valorTotal;
    private String cstICMS;
    private String valorTotalICMS;
    private String cstPIS;
    private String valorPIS;
    private String cstCOFINS;
    private String valorCOFINS;

    // getters e setters
}
