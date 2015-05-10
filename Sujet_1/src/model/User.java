package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = -7351729135012380019L;
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String poste;
	private String biographie;
	private List<Force> competences;
	private List<Force> passions;

	public User(int id, String nom, String prenom, String email, String password, String poste, String biographie) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.poste = poste;
		this.biographie = biographie;
		this.competences = new ArrayList<Force>();
		this.passions = new ArrayList<Force>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public List<Force> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Force> competences) {
		this.competences = competences;
	}

	public List<Force> getPassions() {
		return passions;
	}

	public void setPassions(List<Force> passions) {
		this.passions = passions;
	}
	

}
