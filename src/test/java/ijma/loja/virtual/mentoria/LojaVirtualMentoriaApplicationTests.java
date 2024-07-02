package ijma.loja.virtual.mentoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ijma.loja.virtual.mentoria.controller.AcessoController;
import ijma.loja.virtual.mentoria.model.Acesso;
import ijma.loja.virtual.mentoria.service.AcessoService;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests {
	
	@Autowired
	private AcessoController acessoController; 
	
	@Test
	public void testeCadatradoAcesso() {
		
		Acesso acesso = new Acesso();
		
		acesso.setNomeDescricao("ROLE_SECRETARIO");
		
		acessoController.salvarAcesso(acesso);
		
	}

}




