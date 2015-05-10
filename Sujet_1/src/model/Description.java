package model;

import java.io.Serializable;

public class Description implements Serializable {

	private static final long serialVersionUID = 1997131516321200381L;
	
	private int id;
	private String description;
	
	public Description(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
