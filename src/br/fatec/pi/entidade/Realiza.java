package br.fatec.pi.entidade;
/**
 * Classe de representação da tabela Realiza
 * @author luccame
 */
public class Realiza {
	private int codPessoa, codAula,codRealiza;
	
	public int getCodPessoa() {
		return codPessoa;
	}
	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	/**
	 * Método que pega o codigo da aula
	 * @return int com o código da aula
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
	public int getCodRealiza() {
		return codRealiza;
	}
	public void setCodRealiza(int codRealiza) {
		this.codRealiza = codRealiza;
	}
	
}
