package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO dao;
	Graph<String, DefaultEdge> grafo;
	
	public Model() {
		dao = new WordDAO();
	}
	
	public void createGraph(int numeroLettere) {
		//Prendo tutte le parole di lunghezza numeroLettere
		List<String> paroleLettere = new LinkedList<String>(dao.getAllWordsFixedLength(numeroLettere));
		//Creo il grafo e aggiungo i vertici
		grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(grafo, paroleLettere);
		//Devo aggiungere i vertici ma devo vedere quando
	}

	public List<String> displayNeighbours(String parolaInserita) {
		System.out.println("I vicini di "+parolaInserita+" sono:");
		List<String> vicini = new ArrayList<String>();
		for(DefaultEdge e: grafo.edgeSet()) {
			if(grafo.getEdgeSource(e).equals(parolaInserita))
				vicini.add(grafo.getEdgeSource(e));
			else if(grafo.getEdgeTarget(e).equals(parolaInserita))
				vicini.add(grafo.getEdgeTarget(e));
		}
		System.out.println(vicini);
		return vicini;
	}

	public int findMaxDegree() {
//		grafo.
		return -1;
	}
	
	public boolean areSimilar(String one, String two, int numeroLettere) {
		//Non controllo la lunghezza perché prendo solo parole con la stessa lunghezza
		int counter = 0;
		for(int i=0; i<numeroLettere; i++) {
			if(one.charAt(i) == two.charAt(i))
				counter++;
		}
		if(counter>=(numeroLettere-1))
			return true;
		return false;
	}
}
