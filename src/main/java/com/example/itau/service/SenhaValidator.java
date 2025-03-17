package com.example.itau.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
@Service
public class SenhaValidator implements SenhaValidadorImpl {

    @Async
    public CompletableFuture<Boolean> validarSenha(String senha) {
        return CompletableFuture.supplyAsync(() -> {
            return senha.length() >= 9 &&
                    contemDigito(senha) &&
                    contemLetraMinuscula(senha) &&
                    contemLetraMaiuscula(senha) &&
                    contemCaractereEspecial(senha) &&
                    semCaracteresRepetidos(senha);
        });
    }

    public boolean contemDigito(String senha) {
        return senha.matches(".*\\d.*");
    }

    public boolean contemLetraMinuscula(String senha) {
        return senha.matches(".*[a-z].*");
    }

    public boolean contemLetraMaiuscula(String senha) {
        return senha.matches(".*[A-Z].*");
    }

    public boolean contemCaractereEspecial(String senha) {
        return senha.matches(".*[!@#$%^&*()\\-+].*");
    }

    public boolean semCaracteresRepetidos(String senha) {
        Set<Character> caracteres = new HashSet<>();
        for (char c : senha.toCharArray()) {
            if (!caracteres.add(c)) {
                return false; // Encontrou caractere repetido
            }
        }
        return true; // Não encontrou caracteres repetidos
    }
}
