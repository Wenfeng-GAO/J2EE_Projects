package model;

import java.io.Serializable;

public class Force implements Serializable {
	
	private static final long serialVersionUID = -7211658419688640673L;
	
	int id;
	String titre;
	String description;
	
	public Force(int id, String titre, String description) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		Force force = (Force) obj;
		return this.id == force.getId() && this.titre.equals(force.getTitre()) && this.description.equals(force.getDescription());
	}
	
	

}
