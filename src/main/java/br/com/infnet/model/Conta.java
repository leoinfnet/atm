package br.com.infnet.model;

import java.math.BigDecimal;

public class Conta {
    public String numero;
    String titular;
    private BigDecimal saldo = BigDecimal.ZERO;
    private String senha;

    public Conta(String numero, String titular, String senha) {
        this.numero = numero;
        this.titular = titular;
        this.senha = senha;
        saldo = BigDecimal.ZERO;


    }
    // ⚠️⚠️⚠️ ATENÇÃO ⚠️⚠️⚠️
    // NÃO USE ESTE CONSTRUTOR!
    // Ele está presente apenas por questões ilustrativas e será utilizado internamente para gerar dados de teste.
    // Concentre-se apenas no primeiro construtor com 3 parâmetros (numero, titular, senha) e nos métodos da classe.
    // ⚠️⚠️⚠️ NÃO ALTERE ESTE TRECHO POR ENQUANTO ⚠️⚠️⚠️

    public Conta(String numero, String titular, String senha, BigDecimal saldoInicial) {
        if (numero == null || titular == null || senha == null) {
            throw new IllegalArgumentException("Número, titular e senha são obrigatórios.");
        }
        this.numero = numero;
        this.titular = titular;
        this.senha = senha;
        this.saldo = saldoInicial != null && saldoInicial.compareTo(BigDecimal.ZERO) > 0
                ? saldoInicial
                : BigDecimal.ZERO;
    }

    public void depositar(BigDecimal valor) {
        saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (saldo.compareTo(valor) >= 0) {
            saldo.subtract(valor); // mesmo erro: saldo não muda
        }
    }

    public boolean autenticar(String senhaInformada) {
        return senhaInformada == senha; // problema: compara String com ==
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
