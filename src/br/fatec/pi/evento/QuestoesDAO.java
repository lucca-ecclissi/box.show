package br.fatec.pi.evento;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.fatec.pi.config.BD;
import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Questoes;

/**
 * Classe de Manipulação de tabela questoes
 * @author luccame
 *
 */
public class QuestoesDAO extends Questoes {

	private BD bd = new BD();
	private String sql;
	
    /**
     * Método que executa a query, e retorna um Array de Questoes
     * 
     * @return ArrayList do tipo Questoees.
     */
    public ArrayList<Questoes> executar() {
    	ArrayList<Questoes>  array = new ArrayList<>();
        try {
        	sql = "SELECT q.*, a.* FROM Questoes q "
        			+ "INNER JOIN Aulas a ON q.cod_aula = a.cod_aula "
        			+ "WHERE q.questoes LIKE ? ";
        	if(bd.getConnection()) {
	        	PreparedStatement pre = bd.con.prepareStatement(sql);
	        	pre.setString(1, "%"+getQuestoes()+"%");
	        	bd.rs = pre.executeQuery();
	            while(bd.rs.next()) {
	            	Questoes q = new Questoes();
	            	Aulas a = new Aulas();
	            	q.setCodQuestoes(bd.rs.getInt("cod_questoes"));
	            	q.setQuestoes(bd.rs.getString("questoes"));
	            	q.setCodAula(bd.rs.getInt("cod_aula"));
	            	a.setCodAula(bd.rs.getInt("cod_aula"));
	            	a.setDescricao(bd.rs.getString("descricao"));
	            	a.setNomeAula(bd.rs.getString("nome_aula"));
	            	q.setAulas(a);
	            	array.add(q);
	            }
        	}
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return array;
    }

    /**
     * Método que executa a query, e retorna um Array de Questoes
     * 
     * @return ArrayList do tipo Questoees.
     */
    public ArrayList<Questoes> selecionar() {
    	ArrayList<Questoes>  array = new ArrayList<>();
        try {
        	sql = "SELECT q.*, a.* FROM Questoes q "
        			+ "INNER JOIN Aulas a ON q.cod_aula = a.cod_aula "
        			+ "WHERE a.cod_aula = ? ";
        	if(bd.getConnection()) {
	        	PreparedStatement pre = bd.con.prepareStatement(sql);
	        	pre.setInt(1, getCodAula());
	        	bd.rs = pre.executeQuery();
	            while(bd.rs.next()) {
	            	Questoes q = new Questoes();
	            	Aulas a = new Aulas();
	            	q.setCodQuestoes(bd.rs.getInt("cod_questoes"));
	            	q.setQuestoes(bd.rs.getString("questoes"));
	            	q.setCodAula(bd.rs.getInt("cod_aula"));
	            	a.setCodAula(bd.rs.getInt("cod_aula"));
	            	a.setDescricao(bd.rs.getString("descricao"));
	            	a.setNomeAula(bd.rs.getString("nome_aula"));
	            	q.setAulas(a);
	            	array.add(q);
	            }
        	}
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return array;
    }
    
    /**
     * Executa uma query como update, delete ou insert.
     *
     * @param sql O sql que será executado
     * @return O codigo da questão 
     */
    public int inserir() {
        try {
        	sql = "INSERT INTO Questoes (questoes,cod_aula) VALUES(?,?)";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getQuestoes());
        	pre.setInt(2, getCodAula());
        	
        	if(pre.executeUpdate() != 0) {
        		bd.st = bd.con.createStatement();
        		bd.rs = bd.st.executeQuery("SELECT MAX(cod_questoes) as id FROM Questoes");
        		bd.rs.next();
        		return bd.rs.getInt("id");        		
        	}
        	
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }
    
    /**
     * Executa uma query alterar.
     *
     * @return 0 para um alterar mal sucedido e 1 para bem sucedido
     */
    public int alterar() {
        try {
        	sql = "UPDATE Questoes SET questoes = ? , cod_aula = ?"
        			+ " WHERE cod_questoes= ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getQuestoes());
        	pre.setInt(2, getCodAula());
        	pre.setInt(3, getCodQuestoes());	
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
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
        	sql = "DELETE FROM Questoes "
        			+ " WHERE cod_questoes = ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodQuestoes());	
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }
    
}
