package ijma.loja.virtual.mentoria.enums;

public enum StatusContaReceber {

	COMBRANCA("Pagar"), 
	VENCIDA("Vencida"), 
	ABERTA("Aberta"), 
	QUITADA("Quitada");

	private String nomeDescricao;

	private StatusContaReceber(String nomeDescricao) {
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
