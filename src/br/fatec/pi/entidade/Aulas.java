
package br.fatec.pi.entidade;

/**
 * Classe de representação da tabela Aulas
 * @author luccame
 */
public class Aulas {
	private int codAula;
	private String nomeAula;
	private String descricao;
	private String tipo = "";
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * Método que pega o codigo da aula
	 * @return int com o codigo da aula
	 */
	public int getCodAula() {
		return codAula;
	}
	/**
	 * Método que seta o codigo da aula
	 * @param codAula int com o codigo da aula
	 */
	public void setCodAula(int codAula) {
		this.codAula = codAula;
	}
	/**
	 * Método que pega o nome da aula
	 * @return String com o nome da aula
	 */
	public String getNomeAula() {
		return nomeAula;
	}
	/**
	 * Método que seta o nome da aula
	 * @param nomeAula String com o nome da aula
	 */
	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	/**
	 * Método que pega a descricao da aula
	 * @return String com a descricao da aula
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Método que seta a descricao da aula
	 * @param descricao String com a descricao da aula
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
