package ijma.loja.virtual.mentoria;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import ijma.loja.virtual.mentoria.controller.PessoaController;
import ijma.loja.virtual.mentoria.model.PessoaJuridica;
import junit.framework.TestCase;

@Profile("dev")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private PessoaController pessoaController;

	@Test
	public void testeCadPessoa() throws ExceptionMentoriaJava {

		PessoaJuridica pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("fsdfsdfsfds");
		pessoaJuridica.setEmail("teste@protocolo.com");
		pessoaJuridica.setTelefone("5645454");
		pessoaJuridica.setInscricaoEstadual("21231231231313ffsfs");
		pessoaJuridica.setInscricaoMunicipal("212312312313135345454564564");
		pessoaJuridica.setNomeFantasia("Fsdfsfsdfs fsdfdsfdsfds");
		pessoaJuridica.setRazaoSocial("gfdgdfgfd gfdgfdgfdgdfgfdgdf");

		pessoaController.salvarPj(pessoaJuridica);

	}

}
