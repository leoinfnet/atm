package br.com.infnet.service;

import br.com.infnet.exception.SaldoInsuficienteException;
import br.com.infnet.model.Conta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CaixaEletronicoTest {
    private final CaixaEletronicoService service = new CaixaEletronicoService();
    @ParameterizedTest
    @MethodSource("casosDeSaqueValidos")
    void deveCalcularMenorQuantidadeDeNotas(BigDecimal valor, int[] esperado) {
        Conta conta = new Conta("1234", "Aluno", "senha", new BigDecimal("500"));
        int[] resultado = service.sacar(conta, valor);
        assertArrayEquals(esperado, resultado);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> casosDeSaqueValidos() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(new BigDecimal("74"), new int[]{2, 0, 2, 1}),
                org.junit.jupiter.params.provider.Arguments.of(new BigDecimal("20"), new int[]{0, 0, 2, 0}),
                org.junit.jupiter.params.provider.Arguments.of(new BigDecimal("7"), new int[]{1, 1, 0, 0}),
                org.junit.jupiter.params.provider.Arguments.of(new BigDecimal("100"), new int[]{0, 0, 0, 2}),
                org.junit.jupiter.params.provider.Arguments.of(new BigDecimal("17"), new int[]{1, 1, 1, 0})
        );
    }

    @Test
    void deveLancarExcecaoSeValorNaoPodeSerSacadoComNotasDisponiveis() {
        Conta conta = new Conta("0001", "Erro", "senha", new BigDecimal("500"));
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                service.sacar(conta, new BigDecimal("1"))
        );
        assertTrue(ex.getMessage().contains("Não é possível sacar"));
    }

    @Test
    void deveLancarExcecaoSeSaldoForInsuficiente() {
        Conta conta = new Conta("0002", "Erro", "senha", new BigDecimal("20"));
        assertThrows(SaldoInsuficienteException.class, () ->
                service.sacar(conta, new BigDecimal("100"))
        );
    }

    @Test
    void deveLancarExcecaoSeValorForNegativoOuZero() {
        Conta conta = new Conta("0003", "Erro", "senha", new BigDecimal("100"));

        assertThrows(IllegalArgumentException.class, () ->
                service.sacar(conta, new BigDecimal("-10"))
        );

        assertThrows(IllegalArgumentException.class, () ->
                service.sacar(conta, BigDecimal.ZERO)
        );

        assertThrows(IllegalArgumentException.class, () ->
                service.sacar(conta, null)
        );
    }
}
