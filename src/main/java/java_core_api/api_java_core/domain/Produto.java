package java_core_api.api_java_core.domain;

public class Produto {
    private Long id;
    private String nome;
    private String codigoBarras;  // Código de barras
    private Long fabricanteId;  // Relacionamento com o fabricante, apenas o ID do fabricante

    // Getter para id
    public Long getId() {
        return id;
    }

    // Setter para id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para código de barras
    public String getCodigoBarras() {
        return codigoBarras;
    }

    // Setter para código de barras
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    // Getter para id do fabricante
    public Long getFabricanteId() {
        return fabricanteId;
    }

    // Setter para id do fabricante
    public void setFabricanteId(Long fabricanteId) {
        this.fabricanteId = fabricanteId;
    }
}
