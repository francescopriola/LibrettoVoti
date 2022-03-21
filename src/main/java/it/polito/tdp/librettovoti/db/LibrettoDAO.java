package it.polito.tdp.librettovoti.db;

import java.sql.*;
import java.util.*;
import it.polito.tdp.librettovoti.model.*;

public class LibrettoDAO {
	
	public boolean creaVoto(Voto voto) {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "INSERT INTO voti (nome, punti) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,  voto.getNome());
			st.setInt(2,  voto.getPunti());
			
			int res = st.executeUpdate();	//Esegue l'aggiornamento del db (aggiunge un dato) e restituisce un int
			st.close();
			
			if(res == 1) {
				System.out.println("Dato correttamente inserito");
			}
			
			conn.close();
			return (res == 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Voto> readAllVoto() {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT * FROM voti";	
			PreparedStatement st = conn.prepareStatement(sql);	
			
			ResultSet res = st.executeQuery();	
			List<Voto> result = new ArrayList<Voto>();
			while(res.next()) {
				String nome = res.getString("nome");
				int punti = res.getInt("punti");
				result.add(new Voto(nome, punti));
			}
			st.close();
			conn.close();
			return result;
		}catch(SQLException e) {
			System.out.println(e);	
			e.printStackTrace();
			return null;
		}
	}
	
	public Voto readVotoByNome(String nome) {
		List<Voto> voti = this.readAllVoto();
		for(Voto v : voti) {
			if(v.getNome().equals(nome))
				return v;
		}
		return null;
	}

}
