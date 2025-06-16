package br.com.infnet.util;

import br.com.infnet.model.Conta;
import br.com.infnet.repositorio.ContaRepository;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Random;

public class GeradorDeContasFake {
    public static void gerar(ContaRepository repository) {
        Faker faker = new Faker(new Locale("pt-BR"));
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            String numero = String.format("%04d", random.nextInt(10000));
            String titular = faker.name().fullName();
            String senha = faker.internet().password(6, 10);
            BigDecimal depositoInicial = BigDecimal.valueOf(100 + random.nextInt(4901)); // entre 100 e 5000
            Conta conta = new Conta(numero, titular, senha,depositoInicial);


            repository.salvar(conta);
        }
    }
}
