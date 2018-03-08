package br.fatec.pi.evento;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.fatec.pi.config.BD;
import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Realiza;

/**
 * Classe de Manipulação de tabela realiza
 * @author luccame
 *
 */
public class RealizaDAO extends Realiza {

	private BD bd = new BD();
	private String sql;
	
    /**
     * Método que executa a query, e retorna um Array de Realiza
     * 
     * @param query String para que se deseja executar.
     * @return ArrayList do tipo Realiza.
     */
    public ArrayList<Realiza> executar( String query ) {
    	ArrayList<Realiza>  array = new ArrayList<>();
        try {
            bd.st = bd.con.createStatement();
            bd.rs = bd.st.executeQuery(query);
            while(bd.rs.next()) {
            	Realiza r = new Realiza();
            	r.setCodAula(bd.rs.getInt("cod_aula"));
            	array.add(r);
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
    public ArrayList<Realiza> selecionar() {
    	ArrayList<Realiza>  array = new ArrayList<>();
        try {
        	sql = "SELECT * FROM Realiza "
        			+ "WHERE cod_aula = ?";
        	if(!bd.getConnection())
        		return array;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodAula());
            bd.rs = pre.executeQuery();
            System.out.println("Selecionar");
            while(bd.rs.next()) {
            	System.out.println("OUUU");
            	Realiza a = new Realiza();
            	a.setCodAula(bd.rs.getInt("cod_aula"));
            	a.setCodPessoa(bd.rs.getInt("cod_pessoa"));
            	a.setCodRealiza(bd.rs.getInt("cod_realiza"));
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
        	sql = "INSERT INTO Realiza (cod_pessoa,cod_aula) VALUES(?,?)";
        	if(!bd.getConnection())
        		return 0;
        	PreparedStatement pre = bd.con.prepareStatement(sql);
        	pre.setInt(1, getCodPessoa());
        	pre.setInt(2, getCodAula());
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
