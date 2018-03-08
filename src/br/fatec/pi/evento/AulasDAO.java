package br.fatec.pi.evento;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;

import br.fatec.pi.config.BD;
import br.fatec.pi.entidade.Aulas;

/**
 * Classe de Manipulação de tabela aulas
 * @author luccame
 *
 */
public class AulasDAO extends Aulas{

	private BD bd = new BD();
	private String sql;
	
    /**
     * Método que executa a query, e retorna um Array de Aulas
     * 
     * @param query String para que se deseja executar.
     * @return ArrayList do tipo Aulas.
     */
    public ArrayList<Aulas> executar( String query ) {
    	ArrayList<Aulas>  array = new ArrayList<>();
        try {
            bd.st = bd.con.createStatement();
            bd.rs = bd.st.executeQuery(query);
            while(bd.rs.next()) {
            	Aulas a = new Aulas();
            	a.setCodAula(bd.rs.getInt("cod_aula"));
            	a.setNomeAula(bd.rs.getString("nome_aula"));
            	a.setDescricao(bd.rs.getString("descricao"));
            	a.setTipo(bd.rs.getString("tipo"));
            	array.add(a);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
       
        return array;
    }
   
    /**
     * Método que executa a query, e retorna um Array de Aulas
     * 
     * @return ArrayList do tipo Aulas.
     */
    public ArrayList<Aulas> selecionar() {
    	ArrayList<Aulas>  array = new ArrayList<>();
        try {
        	sql = "SELECT * FROM Aulas";
        	if(!bd.getConnection())
        		return array;
            bd.st = bd.con.createStatement();
            bd.rs = bd.st.executeQuery(sql);
            while(bd.rs.next()) {
            	Aulas a = new Aulas();
            	a.setCodAula(bd.rs.getInt("cod_aula"));
            	a.setNomeAula(bd.rs.getString("nome_aula"));
            	a.setDescricao(bd.rs.getString("descricao"));
            	a.setTipo(bd.rs.getString("tipo"));
            	array.add(a);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return array;
    }
    /**
     * Executa uma query insert.
     *
     * @return 0 para um insert mal sucedido e 1 para bem sucedido
     */
    public int inserir() {
        try {
        	sql = "INSERT INTO Aulas (nome_aula,descricao,tipo) VALUES(?,?,?)";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getNomeAula());
        	pre.setString(2, getDescricao());
        	pre.setString(3, getTipo());
            return pre.executeUpdate();
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
        	sql = "UPDATE Aulas SET nome_aula = ? ,descricao = ?, tipo = ?"
        			+ " WHERE cod_aula = ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getNomeAula());
        	pre.setString(2, getDescricao());
        	pre.setString(3, getTipo());
        	pre.setInt(4, getCodAula());	
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
        	sql = "DELETE FROM Aulas "
        			+ " WHERE cod_aula = ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodAula());	
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }
    
}
