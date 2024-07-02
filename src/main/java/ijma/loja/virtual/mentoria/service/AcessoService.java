package ijma.loja.virtual.mentoria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ijma.loja.virtual.mentoria.model.Acesso;
import ijma.loja.virtual.mentoria.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso save(Acesso acesso) {
		
		// Podemos fazer qualquer tipo de validação antes de salvar
		
		return acessoRepository.save(acesso);

	}

}
