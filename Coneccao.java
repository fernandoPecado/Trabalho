package trabalhopratico;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Coneccao extends JFrame 
{
	
	private Container cont;
	
	private JTable trabalhador;
	private JScrollPane sp;
	private String [] cabecalho = {"Trabalhador_id","Cartao_id","Email","Nota_id", "Chefia","Troca Cartao","Cargo, Endereco, nome"
									+ "Telefone"};
	private String [][] dados;
	
	public Coneccao() 
	{
		super("Visualizacao de trabalhadores");
		cont = getContentPane();
		
		try {
			dados = gerarTabela();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trabalhador = new JTable(dados, cabecalho);
		trabalhador.setEnabled(false);
		sp = new JScrollPane(trabalhador);
                
		cont.add(sp, BorderLayout.CENTER);
		setSize(500,250);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public String[][] gerarTabela() throws ClassNotFoundException, SQLException, IOException
	{
		
		Class.forName ("oracle.jdbc.driver.OracleDriver");	
		Connection con=DriverManager.getConnection(
		"jdbc :oracle:thin:@192.168.18.62:1521:xe", "E20180967", "estudante");
		Statement stmt=con.createStatement();			
		ResultSet r =stmt.executeQuery("SELECT * FROM Trabalhador");
			
		String [][] d = new String[11][cabecalho.length];
	     
		for (int i = 0; i < 11 && r.next(); i++) 
		{
				int id =r.getInt(1);
				int c =r.getInt(2);
				String e = r.getString(3);
				int n = r.getInt(4);
				String h = r.getString(5);
				String t = r.getString(6);
				String a = r.getString(7);
				String es = r.getString(8);
				String m = r.getString(9);
				String f = r.getString(10);
				
				d[i][0] = id+"";
				d[i][1] = c+"";
				d[i][2] = e+"";
				d[i][3] = n+"";	
	            d[i][4] = h+"";
	            d[i][5] = t+"";
	            d[i][6] = a+"";
				d[i][7] = es+"";
				d[i][8] = m+"";
				d[i][9] = f+"";	
				
				
			}		
			 
		r.close();
		
		stmt.close();
		con.close();
	
		return d;
	}	
	
	public static void main (String [] args)
	{
		new Coneccao();
	}
}