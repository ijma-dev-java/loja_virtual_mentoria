package ijma.loja.virtual.mentoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ijma.loja.virtual.mentoria.ExceptionMentoriaJava;
import ijma.loja.virtual.mentoria.model.Acesso;
import ijma.loja.virtual.mentoria.repository.AcessoRepository;
import ijma.loja.virtual.mentoria.service.AcessoService;

// @CrossOrigin(origins = {"https://www.ijmaservicestech.com.br"})
@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;

	@ResponseBody // Retorna o objeto convertido em JSON para API
	@PostMapping(value = "**/salvarAcesso") // Mapeando a URL para receber JSON
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionMentoriaJava {

		if (acesso.getId() == null) {

			List<Acesso> acessos = acessoRepository.buscarAcessoNomeDescricao(acesso.getNomeDescricao().toUpperCase());

			if (!acessos.isEmpty()) {

				throw new ExceptionMentoriaJava("Já existe Acesso com a descrição: " + acesso.getNomeDescricao());

			}
		}

		Acesso acessoSalvo = acessoService.save(acesso);

		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);

	}

	@ResponseBody // Retorna o objeto convertido em JSON para API
	@PostMapping(value = "**/deleteAcesso") // Mapeando a URL para receber JSON
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {

		acessoRepository.deleteById(acesso.getId());

		return new ResponseEntity("Acesso removido com sucesso!", HttpStatus.OK);

	}

	// @Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

		acessoRepository.deleteById(id);

		return new ResponseEntity("Acesso removido com sucesso!", HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/obterAcessoPorId/{id}")
	public ResponseEntity<Acesso> obterAcessoPorId(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

		Acesso acesso = acessoRepository.findById(id).orElse(null);

		if (acesso == null) {

			throw new ExceptionMentoriaJava("Não encontrou Acesso com o código: " + id);

		}

		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscarPorNomeDescricao/{nomeDescricao}")
	public ResponseEntity<List<Acesso>> buscarPorNomeDescricao(@PathVariable("nomeDescricao") String nomeDescricao) {

		List<Acesso> acesso = acessoRepository.buscarAcessoNomeDescricao(nomeDescricao.toUpperCase());

		return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);

	}

}
