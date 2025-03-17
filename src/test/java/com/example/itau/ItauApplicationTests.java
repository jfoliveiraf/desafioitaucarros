package com.example.itau;

import com.example.itau.controller.SenhaController;
import com.example.itau.service.SenhaValidator;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SenhaController.class)
class ItauApplicationTests {

    private final SenhaValidator passwordValidator = new SenhaValidator();
    private final SenhaController Controller = new SenhaController();

    @Test
    public void testValidCOntroller() throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> future = Controller.validarSenha("Exemplo@12");
        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertTrue(future.get());
    }

    @Test
    public void testInvaValidCOntroller() throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> future = Controller.validarSenha("Exemplo@12222222222222");
        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());
    }


    @Test
    public void testValidPassword() throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> future = passwordValidator.validarSenha("Valid1@Password");

        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());
    }

    @Test
    public void testInvalidPasswordTooShort() throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> future = passwordValidator.validarSenha("Short1@");

        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());

    }

    @Test
    public void testInvalidPasswordNoDigit() throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> future = passwordValidator.validarSenha("NoDigit@Password");

        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());



    }

    @Test
    public void testInvalidPasswordNoLowerCase() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = passwordValidator.validarSenha("NODIGIT1@PASSWORD");

        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());


    }

    @Test
    public void testInvalidPasswordNoUpperCase() throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> future = passwordValidator.validarSenha("nodigit1@password");
        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());
    }

    @Test
    public void testInvalidPasswordNoSpecialChar() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = passwordValidator.validarSenha("NoSpecialChar1");
        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());
    }

    @Test
    public void testInvalidPasswordWithRepeats() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = passwordValidator.validarSenha("Repeats1@Password");
        Awaitility.await().atMost(Duration.ofSeconds(10L)).until(future::isDone);
        Assertions.assertFalse(future.get());

    }

}
