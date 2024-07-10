package ijma.loja.virtual.mentoria;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ijma.loja.virtual.mentoria.controller.AcessoController;
import ijma.loja.virtual.mentoria.model.Acesso;
import ijma.loja.virtual.mentoria.repository.AcessoRepository;
import junit.framework.TestCase;

@Profile("dev")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests extends TestCase {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;

	@Autowired
	private WebApplicationContext wac;

	// Testando o end-point salvarAcesso
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {

		// Responsável por efetuar os testes
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();

		Acesso acesso = new Acesso();

		acesso.setNomeDescricao("ROLE_COMPRADOR" + Calendar.getInstance().getTimeInMillis());

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/salvarAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		System.out.println("RETORNO DA API: " + retornoApi.andReturn().getResponse().getContentAsString());

		// Converter um retorno da API para um objeto de acesso

		Acesso objetoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				Acesso.class);

		assertEquals(acesso.getNomeDescricao(), objetoRetorno.getNomeDescricao());

	}

	// Testando o end-point deleteAcesso por nome de descrição
	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {

		// Responsável por efetuar os testes
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();

		Acesso acesso = new Acesso();

		acesso.setNomeDescricao("ROLE_TESTE_DELETE");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/deleteAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		System.out.println("RETORNO DA API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS DE RETORNO: " + retornoApi.andReturn().getResponse().getStatus());

		assertEquals("Acesso removido com sucesso!", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

	}

	// Testando o end-point deleteAcessoPorId/{id} objeto por id
	@Test
	public void testRestApiDeleteAcessoPorId() throws JsonProcessingException, Exception {

		// Responsável por efetuar os testes
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();

		Acesso acesso = new Acesso();

		acesso.setNomeDescricao("ROLE_TESTE_DELETE_POR_ID");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAcessoPorId/" + acesso.getId())
				.content(objectMapper.writeValueAsString(acesso)).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		System.out.println("RETORNO DA API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS DE RETORNO: " + retornoApi.andReturn().getResponse().getStatus());

		assertEquals("Acesso removido com sucesso!", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

	}

	// Testando o end-point obterAcessoPorId/{id} objeto por id
	@Test
	public void testRestApiObterAcessoPorId() throws JsonProcessingException, Exception {

		// Responsável por efetuar os testes
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();

		Acesso acesso = new Acesso();

		acesso.setNomeDescricao("ROLE_TESTE_OBTER_POR_ID");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/obterAcessoPorId/" + acesso.getId())
				.content(objectMapper.writeValueAsString(acesso)).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		// Converter um retorno da API para um objeto de acesso

		Acesso acessoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				Acesso.class);

		assertEquals(acesso.getNomeDescricao(), acessoRetorno.getNomeDescricao());

		assertEquals(acesso.getId(), acessoRetorno.getId());

	}

	// Testando o end-point buscarPorNomeDescricao/{nomeDescricao} objeto por nome
	// de descrição
	@Test
	public void testRestApibuscarPorNomeDescricao() throws JsonProcessingException, Exception {

		// Responsável por efetuar os testes
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();

		Acesso acesso = new Acesso();

		acesso.setNomeDescricao("ROLE_TESTE_BUSCAR_LISTA");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/buscarPorNomeDescricao/BUSCAR_LISTA")
				.content(objectMapper.writeValueAsString(acesso)).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		List<Acesso> retornoApiList = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				new TypeReference<List<Acesso>>() {
				});

		assertEquals(1, retornoApiList.size());

		assertEquals(acesso.getNomeDescricao(), retornoApiList.get(0).getNomeDescricao());

		acessoRepository.deleteById(acesso.getId());

	}

	@Test
	public void testCadastraAcesso() throws ExceptionMentoriaJava {

		String descricaoAcesso = "ROLE_ADMIN_TESTE" + Calendar.getInstance().getTimeInMillis();

		Acesso acesso = new Acesso();

		acesso.setNomeDescricao(descricaoAcesso);

		assertEquals(true, acesso.getId() == null);

		/* Gravou no banco de dados */
		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertEquals(true, acesso.getId() > 0);

		/* Validar dados salvos da forma correta */
		assertEquals(descricaoAcesso, acesso.getNomeDescricao());

		/* Teste de carregamento */

		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

		assertEquals(acesso.getId(), acesso2.getId());

		/* Teste de delete */

		acessoRepository.deleteById(acesso2.getId());

		acessoRepository.flush(); /* Roda esse SQL de delete no banco de dados */

		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);

		assertEquals(true, acesso3 == null);

		/* Teste de query */

		acesso = new Acesso();

		acesso.setNomeDescricao("ROLE_ALUNO");

		acesso = acessoController.salvarAcesso(acesso).getBody();

		List<Acesso> acessos = acessoRepository.buscarAcessoNomeDescricao("ALUNO".trim().toUpperCase());

		assertEquals(1, acessos.size());

		acessoRepository.deleteById(acesso.getId());

	}

}
