package model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -7351729135012380019L;
	
	private String email, password, nom, prenom;

	public User(String email) {
		this.email = email;
		nom = "Dupont";
		prenom = "Jean";
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
	
	

}
