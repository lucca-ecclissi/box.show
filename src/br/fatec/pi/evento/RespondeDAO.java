package br.fatec.pi.evento;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.fatec.pi.config.BD;
import br.fatec.pi.entidade.Responde;

/**
 * Classe de Manipulação de tabela responde
 * @author luccame
 *
 */
public class RespondeDAO extends Responde{

	private BD bd = new BD();
	private String sql;
    /**
     * Método que executa a query, e retorna um Array de Responde
     * 
     * @param query String para que se deseja executar.
     * @return ArrayList do tipo Responde.
     */
    public ArrayList<Responde> executar( String query ) {
    	ArrayList<Responde>  array = new ArrayList<>();
        try {
            bd.st = bd.con.createStatement();
            bd.rs = bd.st.executeQuery(query);
            while(bd.rs.next()) {
            	Responde r = new Responde();
            	r.setCodPessoa(bd.rs.getInt("cod_aluno"));
            	array.add(r);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
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
        	sql = "INSERT INTO Responde (cod_pessoa,cod_questao,sum) VALUES(?,?,?)";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodPessoa());
        	pre.setInt(2, getCodQuestao());
        	pre.setDouble(3, getSum());
            return pre.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
			bd.close();
		}
       
        return 0;
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

}
