package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.domain.Usuario;
import java_core_api.api_java_core.dtos.LoginResponseDTO;
import java_core_api.api_java_core.exception.CredenciaisInvalidasException;
import java_core_api.api_java_core.mapper.UsuarioMapper;
import java_core_api.api_java_core.services.UsuarioService;
import java_core_api.api_java_core.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody Usuario usuario) {
        Usuario user = usuarioMapper.findByUsername(usuario.getEmail());

        if (user != null && passwordEncoder.matches(usuario.getSenha(), user.getSenha())) {
            String token = JwtUtil.generateToken(user);
            return new LoginResponseDTO( user.getId(), token, user.getEmail(), user.getRole());
        }

        throw new CredenciaisInvalidasException();
    }


    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        usuarioService.criarUsuario(usuario);
        return "Usuário cadastrado com sucesso!";
    }



}
