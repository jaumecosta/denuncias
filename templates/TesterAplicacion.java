package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesterAplicacion {

    @PostMapping("/greeting")
    public String greeting(@RequestBody Usuario usuario){
        return "Nombre " + usuario.getName() + ", dni " + usuario.getDni() + ", cumpleaños " + usuario.getBirthdate();
    }
}