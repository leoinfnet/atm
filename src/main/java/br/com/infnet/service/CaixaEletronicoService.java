package br.com.infnet.service;

import br.com.infnet.exception.SaldoInsuficienteException;
import br.com.infnet.model.Conta;

import java.math.BigDecimal;

public class CaixaEletronicoService {
    private static final int[] NOTAS = {2, 5, 10, 50};

    public int[] sacar(Conta conta, BigDecimal valor) {
        return null;
    }
}
