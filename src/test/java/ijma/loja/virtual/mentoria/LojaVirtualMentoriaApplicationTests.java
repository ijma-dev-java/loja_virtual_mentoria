package ijma.loja.virtual.mentoria;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ijma.loja.virtual.mentoria.controller.AcessoController;
import ijma.loja.virtual.mentoria.model.Acesso;
import ijma.loja.virtual.mentoria.repository.AcessoRepository;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests extends TestCase {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;

	@Test
	public void testCadastraAcesso() {

		Acesso acesso = new Acesso(); // Instanciando objeto

		acesso.setNomeDescricao("ROLE_ADMIN"); // Setando dados ao atributo

		assertEquals(true, acesso.getId() == null); // Verificado se tem id

		/* Gravou no banco de dados */
		acesso = acessoController.salvarAcesso(acesso).getBody(); // Gravando no banco de dados

		assertEquals(true, acesso.getId() > 0); // Verificando se gravou

		/* Validar dados salvos da forma correta */
		assertEquals("ROLE_ADMIN", acesso.getNomeDescricao());

		/* Teste de carregamento */

		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get(); // Instanciando novo objeto

		assertEquals(acesso.getId(), acesso2.getId()); // Verificando se o id o acesso é igual o acesso2 que foi gravado

		/* Teste de delete */

		acessoRepository.deleteById(acesso2.getId()); // Deletando objeto por id

		acessoRepository.flush(); // Roda esse SQL de delete no banco de dados

		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null); // Pesquisando por id se não encontrar retorna nulo

		assertEquals(true, acesso3 == null); // Verificando se o acesso3 é null

		/* Teste de query */

		acesso = new Acesso(); // Instanciando novo objeto

		acesso.setNomeDescricao("ROLE_ALUNO"); // Setando dados ao atributo

		acesso = acessoController.salvarAcesso(acesso).getBody(); // Salvando dados do objeto

		List<Acesso> acessos = acessoRepository.buscarAcessoNomeDescricao("ALUNO".trim().toUpperCase()); // Pesquisando

		assertEquals(1, acessos.size()); // Verificando se tem registro

		acessoRepository.deleteById(acesso.getId()); // Deletando por id

	}

}
