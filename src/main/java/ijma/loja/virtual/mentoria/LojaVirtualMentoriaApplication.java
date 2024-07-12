package ijma.loja.virtual.mentoria;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication // Faz o spring boot funcionar
@EnableAsync // Para rodar por baixo dos panos
@EntityScan(basePackages = { "ijma.loja.virtual.mentoria.model" }) // Cria tabela no banco de dados(@Entity)
@ComponentScan(basePackages = { "ijma.*" }) // Scannear nosso projeto e procurar por anotações e recursos que estamos
											// ativando
@EnableJpaRepositories(basePackages = { "ijma.loja.virtual.mentoria.repository" }) // Ativar o pacote de repositorios
@EnableTransactionManagement // Gerenciar as transações com o banco de dados
public class LojaVirtualMentoriaApplication implements AsyncConfigurer {

	public static void main(String[] args) {

		// Iniciando a aplicação
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);

	}

	@Override
	@Bean
	public Executor getAsyncExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Assyncrono Thread");
		executor.initialize();

		return executor;
		
	}

}
