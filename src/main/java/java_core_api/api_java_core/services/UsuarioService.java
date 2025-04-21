package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.Usuario;
import java_core_api.api_java_core.exception.EmailJaExisteException;
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
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        Usuario existente = usuarioMapper.findByUsername(usuario.getEmail());

        if (existente != null) {
            throw new EmailJaExisteException("E-mail já está em uso");
        }


        usuarioMapper.insertUsuario(usuario);
    }



    public Usuario obtemUserPorId(Long id) {

       Usuario user =  usuarioMapper.obtemUserPorId(id);

       return user;
    }
}
