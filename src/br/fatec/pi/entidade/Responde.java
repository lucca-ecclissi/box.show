package br.fatec.pi.entidade;

/**
 * Classe de representação da tabela Responde
 * @author luccame
 */
public class Responde {
	private int codPessoa,codQuestao;
	private double sum;
	

	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	/**
	 * Método que pega o codigo da questao
	 * @return int com o codigo da questao
	 */
	public int getCodQuestao() {
		return codQuestao;
	}
	/**
	 * Método que seta o codigo da questao
	 * @param codQuestao int com o codigo da questao
	 */
	public void setCodQuestao(int codQuestao) {
		this.codQuestao = codQuestao;
	}
	/**
	 * Método que pega o codigo da pessoa
	 * @return int com o código da pessoa
	 */
	public int getCodPessoa() {
		return codPessoa;
	}
	/**
	 * Método que seta o codigo da pessoa
	 * @param codPessoa int com o codigo da pessoa
	 */
	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}
	
}
