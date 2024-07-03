package ijma.loja.virtual.mentoria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ijma.loja.virtual.mentoria.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
	
	@Query("select a from Acesso a where upper(trim(a.nomeDescricao)) like %?1%")
	List<Acesso> buscarAcessoNomeDescricao(String nomeDescricao);

}
