package java_core_api.api_java_core.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {


    @GetMapping("/me")
    public String rotaPrivada() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Acesso liberado! Usuário autenticado: " + email;
    }


}
