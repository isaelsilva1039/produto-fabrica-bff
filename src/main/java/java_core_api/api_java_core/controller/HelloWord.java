package java_core_api.api_java_core.controller;

import java_core_api.api_java_core.domain.User;
import java_core_api.api_java_core.services.HelloWordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-word")
public class HelloWord {

    @Autowired
    private  HelloWordServices helloWordServices;

    @GetMapping
    public String helloWord()
    {
        return helloWordServices.hellorWord("Isael");
    }

    @PostMapping("/{id}")
    public String helloWordPost(@PathVariable("id") String id, @RequestParam(value = "filter", defaultValue = "nenhum") String filter , @RequestBody User body)
    {
        return "Hello word " + body.getName() + " e o id é "  + id + "E o filter é " + filter;
    }

}
