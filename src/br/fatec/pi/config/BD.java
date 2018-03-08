package br.fatec.pi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de conexão com o banco
 * @author luccame
 */
public class BD {

	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String DB = "Boxshadow";
	private final String HOST = "localhost";
	private final String LOGIN = "sa";
	private final String SENHA = "RobinHood1234";
	private final String URL = "jdbc:sqlserver://"+HOST+":1433;databaseName=" +DB;
	
	public Connection con;
    public Statement st;
    public ResultSet rs;
    
    /**
	 * Realiza a conexão com o banco de dados
	 * @return - true em caso de sucesso, ou false caso contrario
	 */
    public boolean getConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            this.con = DriverManager.getConnection(URL,LOGIN, SENHA);
            System.out.println("Conectou");       
            return true;
        } catch(Exception e ) {
            System.out.println(e);
        }
        return false;
    }
   
    /**
	 * Encerra a conexão ao banco de dados
	 */
    public void close() {
    	try{
			if(con!=null) con.close();
			System.out.println("Desconectou!");
		}catch(SQLException erro){
			System.out.println(erro.toString());
		}
		try{ if(st!=null) st.close(); 
		} catch(SQLException erro){
			System.out.println(erro.toString());
		}
		try{ if(rs!=null) rs.close(); 
		} catch(SQLException erro){
			System.out.println(erro.toString());
		}
    }
}
