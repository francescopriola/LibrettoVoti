package it.polito.tdp.librettovoti.model;

import java.util.*;
import it.polito.tdp.librettovoti.db.*;

public class Libretto {

	//Questa classe è il MODEL del nostro controller
	public Libretto() {
	
	}
	
	public boolean add(Voto v) {
		LibrettoDAO dao = new LibrettoDAO();
		boolean result = dao.creaVoto(v);
		return result;
	}
	
	public List<Voto> getVoti(){
		LibrettoDAO dao = new LibrettoDAO();
		return dao.readAllVoto();
	}
}