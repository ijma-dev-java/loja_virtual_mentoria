package ijma.loja.virtual.mentoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ijma.loja.virtual.mentoria.model.Pessoa;
import ijma.loja.virtual.mentoria.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public PessoaJuridica existeCnpjCadastrado(String cnpj);

}
