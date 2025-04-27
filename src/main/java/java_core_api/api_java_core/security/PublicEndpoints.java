package java_core_api.api_java_core.security;

import java.util.List;

public class PublicEndpoints {
    public static final List<String> PUBLIC_URLS = List.of(
            "/auth/login",
            "/auth/register",
            "/nf/listar/pendente",
            "/api/itens/",
            "/api/importacao/nota-fiscal"
    );
}
