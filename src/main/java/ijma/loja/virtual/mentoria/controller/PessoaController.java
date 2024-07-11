package ijma.loja.virtual.mentoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ijma.loja.virtual.mentoria.ExceptionMentoriaJava;
import ijma.loja.virtual.mentoria.model.PessoaJuridica;
import ijma.loja.virtual.mentoria.repository.PessoaRepository;
import ijma.loja.virtual.mentoria.service.PessoaUserService;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaUserService pessoaUserService;

	@ResponseBody
	@PostMapping(value = "**/salvarPj")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica)
			throws ExceptionMentoriaJava {

		if (pessoaJuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa juridica não pode ser NULL");
		}

		if (pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
		}
		
		if (pessoaJuridica.getId() == null && pessoaRepository.existeEmailCadastrado(pessoaJuridica.getEmail()) != null) {
			throw new ExceptionMentoriaJava("Já existe E-MAIL cadastrado com os dados: " + pessoaJuridica.getEmail());
		}

		pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);

	}

}
