package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.domain.Usuario;
import java_core_api.api_java_core.dtos.LoginResponseDTO;
import java_core_api.api_java_core.dtos.UsuarioDTO;
import java_core_api.api_java_core.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/me")
    public UsuarioDTO me() {

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Usuario user = usuarioService.obtemUserPorId(userId);

        return new UsuarioDTO( user.getId() , user.getEmail(), user.getRole());
    }

}
