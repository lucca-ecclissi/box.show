package br.fatec.pi.entidade;

public class Respostas {
	private String resposta = "";
	private String obsResposta = "";
	private String tipo = "";
	private int codQuestoes;
	private int codRespostas;
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public String getObsResposta() {
		return obsResposta;
	}
	public void setObsResposta(String obsResposta) {
		this.obsResposta = obsResposta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCodQuestoes() {
		return codQuestoes;
	}
	public void setCodQuestoes(int codQuestoes) {
		this.codQuestoes = codQuestoes;
	}
	public int getCodRespostas() {
		return codRespostas;
	}
	public void setCodRespostas(int codRespostas) {
		this.codRespostas = codRespostas;
	}

}
