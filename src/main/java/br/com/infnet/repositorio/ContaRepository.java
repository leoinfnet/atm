package br.com.infnet.repositorio;

import br.com.infnet.model.Conta;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ContaRepository {
    private final Map<String, Conta> contas = new ConcurrentHashMap<>();

    public void salvar(Conta conta) {
        contas.put(conta.numero, conta); // nota: campo numero ainda é público!
    }
    public List<Conta> buscarTodas() {
        return contas.values().stream().toList();
    }

    public Conta buscarPorNumero(String numero) {
        return contas.get(numero); // pode retornar null
    }

    public boolean existe(String numero) {
        return contas.containsKey(numero);
    }

    public void limpar() {
        contas.clear();
    }

    public int totalDeContas() {
        return contas.size();
    }
}
