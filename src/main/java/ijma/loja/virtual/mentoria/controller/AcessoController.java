package ijma.loja.virtual.mentoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ijma.loja.virtual.mentoria.model.Acesso;
import ijma.loja.virtual.mentoria.repository.AcessoRepository;
import ijma.loja.virtual.mentoria.service.AcessoService;

@Controller
@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@ResponseBody // Retorna o objeto convertido em JSON para API 
	@PostMapping(value = "**/salvarAcesso") // Mapeando a URL para receber JSON
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { // Recebe o JSON e converte para objeto
		
		Acesso acessoSalvo = acessoService.save(acesso); // Recebe o objeto salvo
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK); // Retorna o objeto salvo
		
	}
	
	@ResponseBody // Retorna o objeto convertido em JSON para API 
	@PostMapping(value = "**/deleteAcesso") // Mapeando a URL para receber JSON
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { // Recebe o JSON e converte para objeto
		
		acessoRepository.deleteById(acesso.getId());  // Recebe o objeto salvo para deletar
		
		return new ResponseEntity("Acesso removido com sucesso!", HttpStatus.OK); // Deleta o objeto salvo
		
	}

}
