package com.example.itau.controller;

import com.example.itau.entity.Password;
import com.example.itau.service.SenhaValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/validar")
public class SenhaController {

    @PostMapping
    public CompletableFuture<Boolean> validarSenha(@RequestParam("senha") String senha) {
        Password passwordEntity = new Password(senha);
        SenhaValidator senhaValidator1 = new SenhaValidator();
        CompletableFuture<Boolean> booleanCompletableFuture = senhaValidator1.validarSenha(passwordEntity.getValue());
        return booleanCompletableFuture;
    }
}

