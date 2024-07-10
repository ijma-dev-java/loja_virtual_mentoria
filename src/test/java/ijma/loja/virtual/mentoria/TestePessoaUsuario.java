package ijma.loja.virtual.mentoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import ijma.loja.virtual.mentoria.model.PessoaJuridica;
import ijma.loja.virtual.mentoria.repository.PessoaRepository;
import ijma.loja.virtual.mentoria.service.PessoaUserService;
import junit.framework.TestCase;

@Profile("dev")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private PessoaUserService pessoaUserService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	public void testeCadPessoa() {

		PessoaJuridica pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setCnpj("21231231231313");
		pessoaJuridica.setNome("fsdfsdfsfds");
		pessoaJuridica.setEmail("21231231231313@fsfsdfdsf.com");
		pessoaJuridica.setTelefone("5645454");
		pessoaJuridica.setInscricaoEstadual("21231231231313ffsfs");
		pessoaJuridica.setInscricaoMunicipal("212312312313135345454564564");
		pessoaJuridica.setNomeFantasia("Fsdfsfsdfs fsdfdsfdsfds");
		pessoaJuridica.setRazaoSocial("gfdgdfgfd gfdgfdgfdgdfgfdgdf");

		pessoaRepository.save(pessoaJuridica);

		/*
		 * PessoaFisica pessoaFisica = new PessoaFisica();
		 * 
		 * pessoaFisica.setCpf("54564564564564"); pessoaFisica.setNome("fsdfsdfdsfds");
		 * pessoaFisica.setEmail("ffsdfsdfds@ffsfssd.com");
		 * pessoaFisica.setTelefone("1231231231");
		 * pessoaFisica.setEmpresa(pessoaFisica);
		 */

	}

}
