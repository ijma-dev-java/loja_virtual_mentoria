package ijma.loja.virtual.mentoria.enums;

public enum StatusContaPagar {

	COMBRANCA("Pagar"), 
	VENCIDA("Vencida"), 
	ABERTA("Aberta"), 
	QUITADA("Quitada"),
	ALUGUEL("Aluguel"),
	FUNCIONARIO("Funcionário"),
	NEGOCIADA("Renegociada");

	private String nomeDescricao;

	private StatusContaPagar(String nomeDescricao) {
		this.nomeDescricao = nomeDescricao;
	}

	public String getNomeDescricao() {
		return nomeDescricao;
	}

	@Override
	public String toString() {
		return this.getNomeDescricao();
	}

}
