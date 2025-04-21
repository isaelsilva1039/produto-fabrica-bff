package java_core_api.api_java_core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// dto/NotaFiscalImportacaoDTO.java
@Getter
@Setter
@AllArgsConstructor
public class NotaFiscalImportacaoDTO {
    private EmitenteDTO emitente;
    private List<ProdutoImportadoDTO> produtos;

    // getters e setters
}
