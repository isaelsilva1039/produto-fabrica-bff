package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Usuario;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsuarioMapper {
    Usuario findByUsername(String email);

    void insertUsuario(Usuario usuario);

    Usuario obtemUserPorId (Long id);



}