package br.com.infnet.controller;

import br.com.infnet.model.Conta;
import br.com.infnet.repositorio.ContaRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ContaController {
    private final ContaRepository repository;

    public ContaController(ContaRepository repository) {
        this.repository = repository;
    }

    public void registrarRotas(Javalin app) {
        app.post("/contas", this::criarConta);
        app.post("/contas/{numero}/depositar", this::depositar);
        app.get("/contas/{numero}/saldo", this::verSaldo);
        app.get("/contas", this::listarContas);
    }
    private void listarContas(Context ctx) {
        List<Conta> todas = repository.buscarTodas();
        ctx.json(todas); // ⚠️ devolve senha junto — problema intencional
    }
    private void criarConta(Context ctx) {
        Conta conta = ctx.bodyAsClass(Conta.class);
        repository.salvar(conta);
        ctx.status(201).json(Map.of("mensagem", "Conta criada"));
    }
    private void depositar(Context ctx) {
        String numero = ctx.pathParam("numero");
        Conta conta = repository.buscarPorNumero(numero);
        if (conta == null) {
            ctx.status(404).json(Map.of("erro", "Conta não encontrada"));
            return;
        }

        Map<String, Object> body = ctx.bodyAsClass(Map.class);
        BigDecimal valor;
        try {
            valor = new BigDecimal(body.get("valor").toString());
        } catch (Exception e) {
            ctx.status(400).json(Map.of("erro", "Valor inválido"));
            return;
        }

        conta.depositar(valor);
        ctx.json(Map.of("mensagem", "Depósito realizado"));
    }

    private void verSaldo(Context ctx) {
        String numero = ctx.pathParam("numero");
        Conta conta = repository.buscarPorNumero(numero);
        if (conta == null) {
            ctx.status(404).json(Map.of("erro", "Conta não encontrada"));
            return;
        }

        ctx.json(conta);
    }
}
