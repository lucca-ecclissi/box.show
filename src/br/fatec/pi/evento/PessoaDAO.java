package br.fatec.pi.evento;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.fatec.pi.config.BD;
import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Pessoa;

/**
 * Classe de Manipulação de tabela aluno
 * @author Lucas
 *
 */
public class PessoaDAO extends Pessoa {

	private BD bd = new BD();
	private String sql;
	
	public boolean login() {
		try {
        	sql = "SELECT * FROM Pessoa "
        			+ "WHERE email_pessoa = ? "
        			+ "AND senha_pessoa = ?";
        	if(!bd.getConnection())
        		return false;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getEmailPessoa());
        	pre.setString(2, getSenhaPessoa());
        	bd.rs = pre.executeQuery();
            
            if(bd.rs.next()) {
            	setCodPessoa(bd.rs.getInt("cod_pessoa"));
            	setEmailPessoa(bd.rs.getString("email_pessoa"));
            	setSenhaPessoa(bd.rs.getString("senha_pessoa"));
            	setNomePessoa(bd.rs.getString("nome_pessoa"));
            	return true;
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       return false;
	}
	
	/**
     * Executa uma query insert.
     *
     * @return 0 para um insert mal sucedido e 1 para bem sucedido
     */
    public int cadastrar() {
        try {
        	sql = "INSERT INTO Pessoa (nome_pessoa,email_pessoa,telefone_pessoa,senha_pessoa,tipo) VALUES(?,?,?,?,?)";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getNomePessoa());
        	pre.setString(2, getEmailPessoa());
        	pre.setString(3, getTelefonePessoa());
        	pre.setString(4, getSenhaPessoa());
        	pre.setString(5, getTipo());
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }
	
    /**
     * Método que executa a query, e retorna um Array de Pessoas
     * 
     * @return ArrayList do tipo Pessoa.
     */
    public ArrayList<Pessoa> executar() {
    	ArrayList<Pessoa>  array = new ArrayList<>();
        try {
        	sql = "SELECT * FROM Pessoa "
        			+ "WHERE ( nome_pessoa LIKE ? "
        			+ "OR email_pessoa LIKE ? )"
        			+ "AND tipo = 'A' ";
        	if(!bd.getConnection())
        		return array;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, "%"+getNomePessoa()+"%");
        	pre.setString(2, "%"+getEmailPessoa()+"%");
        	bd.rs = pre.executeQuery();
            while(bd.rs.next()) {
            	Pessoa a = new Pessoa();
            	a.setEmailPessoa(bd.rs.getString("email_pessoa"));
            	a.setSenhaPessoa(bd.rs.getString("senha_pessoa"));
            	a.setNomePessoa(bd.rs.getString("nome_pessoa"));
            	a.setTelefonePessoa(bd.rs.getString("telefone_pessoa"));
            	a.setCodPessoa(bd.rs.getInt("cod_pessoa"));
            	array.add(a);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
       
        return array;
    }
   
    /**
     * Executa uma query como update, delete ou insert.
     *
     * @param sql O sql que será executado
     * @return 0 para um insert mal sucedido e 1 para bem sucedido
     */
    public int inserir( String sql) {
       
        try {
        	bd.st = bd.con.createStatement();
            return bd.st.executeUpdate(sql);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
       
        return 0;
    }  

    /**
     * Executa uma query remover.
     *
     * @return 0 para um remover mal sucedido e 1 para bem sucedido
     */
    public int remover() {
        try {
        	sql = "DELETE FROM Pessoa"
        			+ " WHERE cod_pessoa = ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodPessoa());	
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }

}
