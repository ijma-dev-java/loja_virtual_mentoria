package ijma.loja.virtual.mentoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication // Faz o spring boot funcionar
@EntityScan(basePackages = { "ijma.loja.virtual.mentoria.model" }) // Cria tabela no banco de dados(@Entity)
@ComponentScan(basePackages = {"ijma.*"}) // Scannear nosso projeto e procurar por anotações e recursos que estamos ativando
@EnableJpaRepositories(basePackages = {"ijma.loja.virtual.mentoria.repository"}) // Ativar o pacote de repositorios
@EnableTransactionManagement // Gerenciar as transações com o banco de dados
public class LojaVirtualMentoriaApplication {

	public static void main(String[] args) {

		// Iniciando a aplicação
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);

	}

}
