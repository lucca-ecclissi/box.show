package br.fatec.pi.evento;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.fatec.pi.config.BD;
import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Questoes;
import br.fatec.pi.entidade.Respostas;

public class RespostasDAO extends Respostas {

	private BD bd = new BD();
	private String sql;
	   
    /**
     * Método que executa a query, e retorna um Array de Aulas
     * 
     * @return ArrayList do tipo Aulas.
     */
    public ArrayList<Respostas> selecionar() {
    	ArrayList<Respostas>  array = new ArrayList<>();
        try {
        	sql = "SELECT * FROM Respostas"
        			+ " WHERE cod_questoes = ?";
        	if(bd.getConnection()) {
	        	PreparedStatement pre = bd.con.prepareStatement(sql);
	        	pre.setInt(1, getCodQuestoes());
	        	System.out.println("TEste");
	        	bd.rs = pre.executeQuery();
	            while(bd.rs.next()) {
	            	Respostas r = new Respostas();
	            	r.setCodQuestoes(bd.rs.getInt("cod_questoes"));
	            	r.setObsResposta(bd.rs.getString("obs_resposta"));
	            	r.setResposta(bd.rs.getString("resposta"));
	            	r.setTipo(bd.rs.getString("tipo"));
	            	r.setCodRespostas(bd.rs.getInt("cod_respostas"));
	            	array.add(r);
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
     * Executa uma query alterar.
     *
     * @return 0 para um alterar mal sucedido e 1 para bem sucedido
     */
    public int alterar() {
        try {
        	sql = "UPDATE Respostas SET resposta = ? , "
        			+ "obs_resposta = ?, "
        			+ "tipo = ? "
        			+ " WHERE cod_respostas= ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setString(1, getResposta());
        	pre.setString(2, getObsResposta());
        	pre.setString(3, getTipo());
        	pre.setInt(4, getCodRespostas());
        	System.out.println(getCodRespostas()+" - "+getObsResposta()+" - "+getTipo());
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
        	sql = "DELETE FROM Respostas "
        			+ " WHERE cod_respostas = ?;";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodRespostas());	
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }
    
    /**
     * Executa uma query insert.
     *
     * @return 0 para um insert mal sucedido e 1 para bem sucedido
     */
    public int inserirArray(ArrayList<Respostas> array) {
        try {
        	sql = "INSERT INTO Respostas (resposta,obs_resposta,tipo,cod_questoes) VALUES";
        	for (Respostas respostas : array) {
				sql+="(?,?,?,?),";
			}
        	sql  = sql.substring(0, sql.length()-1);
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	int i = 0;
        	for (Respostas respostas : array) {
        		pre.setString((i+1), respostas.getResposta());
        		pre.setString((i+2), respostas.getObsResposta());
        		pre.setString((i+3), respostas.getTipo());
        		pre.setInt((i+4), getCodQuestoes());
				i+=4;
			}
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
    }
    
}
