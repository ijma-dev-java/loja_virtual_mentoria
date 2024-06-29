package ijma.loja.virtual.mentoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication // Faz o spring boot funcionar
@EntityScan(basePackages = { "ijma.loja.virtual.mentoria.model" }) // Cria tabela no banco de dados(@Entity)
public class LojaVirtualMentoriaApplication {

	public static void main(String[] args) {

		// Iniciando a aplicação
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);

	}

}
