package java_core_api.api_java_core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String email;
    private String role;
}
