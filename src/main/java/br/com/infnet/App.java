package br.com.infnet;

import br.com.infnet.config.GsonMapper;
import br.com.infnet.controller.ContaController;
import br.com.infnet.repositorio.ContaRepository;
import br.com.infnet.util.GeradorDeContasFake;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        ContaRepository contaRepository = new ContaRepository();
        GeradorDeContasFake.gerar(contaRepository);

        ContaController controller = new ContaController(contaRepository);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new GsonMapper(gson));
        }).start(9090);

        controller.registrarRotas(app);

        System.out.println("🚀 ATM API rodando em http://localhost:9090");
    }
}
