package java_core_api.api_java_core.enums;

public enum StatusNotaFiscal {
    PENDENTE(0),
    PROCESSADO(1);

    private final int codigo;

    StatusNotaFiscal(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static StatusNotaFiscal fromCodigo(int codigo) {
        for (StatusNotaFiscal status : values()) {
            if (status.codigo == codigo) return status;
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
