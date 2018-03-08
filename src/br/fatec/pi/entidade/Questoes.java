package br.fatec.pi.entidade;

/**
 * Classe de representação da tabela Questoes
 * @author luccame
 */
public class Questoes {
	
	private String questoes;
	private int codQuestoes,codAula;
	private Aulas aulas;
	
	public Aulas getAulas() {
		return aulas;
	}
	public void setAulas(Aulas aulas) {
		this.aulas = aulas;
	}
	public int getCodAula() {
		return codAula;
	}
	public void setCodAula(int codAula) {
		this.codAula = codAula;
	}
	/**
	 * Método que pega as questoes
	 * @return String com as questoes
	 */
	public String getQuestoes() {
		return questoes;
	}
	/**
	 * Método que seta os questoes
	 * @param questoes
	 */
	public void setQuestoes(String questoes) {
		this.questoes = questoes;
	}

	/**
	 * Método que pega o codigo das questoes
	 * @return int com o codigo das questoes
	 */
	public int getCodQuestoes() {
		return codQuestoes;
	}
	/**
	 * Método que seta o codigo das questoes
	 * @param codQuestoes int com o codigo das questoes
	 */
	public void setCodQuestoes(int codQuestoes) {
		this.codQuestoes = codQuestoes;
	}
}
