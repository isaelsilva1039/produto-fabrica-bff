package java_core_api.api_java_core.dtos;

public class ProdutoDTO {

    private String nome;
    private String codigoBarras;
    private Long fabricanteId;

    // Construtores
    public ProdutoDTO(String nome, String codigoBarras, Long fabricanteId) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.fabricanteId = fabricanteId;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getFabricanteId() {
        return fabricanteId;
    }

    public void setFabricanteId(Long fabricanteId) {
        this.fabricanteId = fabricanteId;
    }
}
