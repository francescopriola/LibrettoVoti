package it.polito.tdp.librettovoti.db;

import java.sql.*;

public class ProvaVoti {
	
	public void aggiungiVoto(String nome, int punti) {
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=Ciccio.p00";
		try {
			Connection conn = DriverManager.getConnection(url);
			String sql = "INSERT INTO voti (nome, punti) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,  nome);
			st.setInt(2,  punti);
			
			int res = st.executeUpdate();	//Esegue l'aggiornamento del db (aggiunge un dato) e restituisce un int
			st.close();
			if(res == 1) {
				System.out.println("Dato correttamente inserito");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) {
		
		ProvaVoti provaVoti = new ProvaVoti();
		provaVoti.aggiungiVoto("Tecniche di Programmazione" , 30);
		
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=Ciccio.p00";
		
		try {
			Connection conn = DriverManager.getConnection(url);	//Apro la connessione
			Statement st = conn.createStatement();	//Restituisce un oggetto di tipo Statemant
			String sql = "SELECT * FROM voti";	//Scrivo la query
			ResultSet res = st.executeQuery(sql);	//Esegue la query e ritorna il risultato della classe ResultSet
			
			while(res.next()) {
				String nome = res.getString("nome");
				int voto = res.getInt("punti");
				System.out.println(nome + " " + voto);
			}
			st.close();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
