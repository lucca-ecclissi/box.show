package br.fatec.pi.entidade;

/**
 * Classe de representação da tabela Aluno
 * @author Lucas
 */
public class Pessoa {
	
	private String nomePessoa ="",emailPessoa="",telefonePessoa="",senhaPessoa="",tipo="";
	private int codPessoa;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * Método que pega o nome do aluno
	 * @return String com o nome do aluno
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}
	/**
	 * Método que seta o nome do aluno
	 * @param nomeAluno String com o nome do aluno
	 */
	public void setNomePessoa(String nomeAluno) {
		this.nomePessoa = nomeAluno;
	}
	/**
	 * Método que pega o email do aluno
	 * @return String com o email do aluno
	 */
	public String getEmailPessoa() {
		return emailPessoa;
	}
	/**
	 * Método que seta o email do aluno
	 * @param emailAluno String com o email do aluno
	 */
	public void setEmailPessoa(String emailAluno) {
		this.emailPessoa = emailAluno;
	}
	/**
	 * Método que pega o telefone do aluno
	 * @return String com o telefone do aluno
	 */
	public String getTelefonePessoa() {
		return telefonePessoa;
	}
	/**
	 * Método que seta o telefone do aluno
	 * @param telefoneAluno String telefone do aluno
	 */
	public void setTelefonePessoa(String telefoneAluno) {
		this.telefonePessoa = telefoneAluno;
	}
	/**
	 * Método que pega a senha do aluno
	 * @return String com a senha do aluno
	 */
	public String getSenhaPessoa() {
		return senhaPessoa;
	}
	/**
	 * Método que seta a senha do aluno
	 * @param senhaAluno String com a senha do aluno
	 */
	public void setSenhaPessoa(String senhaAluno) {
		this.senhaPessoa = senhaAluno;
	}
	/**
	 * Método que pega o codigo do aluno
	 * @return int com o código do aluno
	 */
	public int getCodPessoa() {
		return codPessoa;
	}
	/**
	 * Método que seta o codigo do aluno
	 * @param codAluno int com o codigo do aluno
	 */
	public void setCodPessoa(int codAluno) {
		this.codPessoa = codAluno;
	}
	
}
