package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.Usuario;
import java_core_api.api_java_core.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void criarUsuario(Usuario usuario) {
        // Criptografa a senha antes de salvar
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioMapper.insertUsuario(usuario);
    }
}
