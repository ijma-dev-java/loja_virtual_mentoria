package ijma.loja.virtual.mentoria;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import ijma.loja.virtual.mentoria.controller.PessoaController;
import ijma.loja.virtual.mentoria.enums.TipoEndereco;
import ijma.loja.virtual.mentoria.model.Endereco;
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
		pessoaJuridica.setNome("Teste");
		pessoaJuridica.setEmail("testeadasdasd@gmail.com");
		pessoaJuridica.setTelefone("81789456123");
		pessoaJuridica.setInscricaoEstadual("32123123131");
		pessoaJuridica.setInscricaoMunicipal("645645645");
		pessoaJuridica.setNomeFantasia("Fsfsfsdf dsfdsfsdfdsfsfd");
		pessoaJuridica.setRazaoSocial("Fsfsfsdf dsfdsfsdfdsfsfd LTDA.");
		pessoaJuridica.setTipoPessoa("JURIDICA");
		pessoaJuridica.setEmpresa(pessoaJuridica);
		pessoaJuridica.setCategoria("MEI");

		Endereco enderecoCobranca = new Endereco();

		enderecoCobranca.setPessoa(pessoaJuridica);
		enderecoCobranca.setEmpresa(pessoaJuridica);
		enderecoCobranca.setCep("56454564");
		enderecoCobranca.setLogradouro("56454");
		enderecoCobranca.setNumero("5454544c");
		enderecoCobranca.setComplemento("fdsfsdfdsfs");
		enderecoCobranca.setBairro("fdfdssdfsdfdf");
		enderecoCobranca.setCidade("fdsffdsfdsf");
		enderecoCobranca.setUf("PE");
		enderecoCobranca.setTipoEndereco(TipoEndereco.COBRANCA);

		Endereco enderecoEntrega = new Endereco();

		enderecoEntrega.setPessoa(pessoaJuridica);
		enderecoEntrega.setEmpresa(pessoaJuridica);
		enderecoEntrega.setCep("231231");
		enderecoEntrega.setLogradouro("dfgdfgddf");
		enderecoEntrega.setNumero("23");
		enderecoEntrega.setComplemento("ggdgdgdfg");
		enderecoEntrega.setBairro("fdfdssdggdgdfgdfdfsdfdf");
		enderecoEntrega.setCidade("tertertertk");
		enderecoEntrega.setUf("PE");
		enderecoEntrega.setTipoEndereco(TipoEndereco.ENTREGA);

		pessoaJuridica.getEnderecos().add(enderecoCobranca);
		pessoaJuridica.getEnderecos().add(enderecoEntrega);

		pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();

		// Verifica se cadastrou a pessoaJuridica
		assertEquals(true, pessoaJuridica.getId() > 0);

		// Verifica se cadastrou os endereços
		for (Endereco endereco : pessoaJuridica.getEnderecos()) {

			assertEquals(true, endereco.getId() > 0);

		}

		// Verifica se cadastrou realmente os endereços de cobrança e entrega
		assertEquals(2, pessoaJuridica.getEnderecos().size());

	}

}
