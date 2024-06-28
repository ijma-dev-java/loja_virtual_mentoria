package ijma.loja.virtual.mentoria.enums;

public enum TipoEndereco {

	COBRANCA("Cobrança"), ENTREGA("Entregs");

	private String nomeDescricao;

	private TipoEndereco(String nomeDescricao) {
		this.nomeDescricao = nomeDescricao;
	}

	public String getNomeDescricao() {
		return nomeDescricao;
	}

	@Override
	public String toString() {
		return this.nomeDescricao;
	}

}
