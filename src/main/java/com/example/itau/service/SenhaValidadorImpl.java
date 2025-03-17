package com.example.itau.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface SenhaValidadorImpl {

     CompletableFuture<Boolean> validarSenha(String senha);

     boolean contemDigito(String senha) ;

     boolean contemLetraMinuscula(String senha);

     boolean contemLetraMaiuscula(String senha) ;

     boolean contemCaractereEspecial(String senha);

     boolean semCaracteresRepetidos(String senha) ;

}
