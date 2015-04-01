package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import model.db.DocumentDB;
import model.db.EvaluationDB;
import model.exception.InvalidDataException;

public class Project implements Serializable {

	private static final long serialVersionUID = 2180069907986538519L;

	private String acronym;
	private String description;
	private int fundingDuration;
	private double budget;
	private Date created;
	private Owner owner;
	private Category category;
	private int id;

	public Project(String acronym, String description, int fundingDuration,
			double budget, Owner owner, Category category) throws InvalidDataException {
		setAcronym(acronym);
		setDescription(description);
		setBudget(budget);
		setFundingDuration(fundingDuration);
		setCreated(new Date());
		setOwner(owner);
		setCategory(category);
	}
	

	
	public double getAttractiveness() {
		List<Evaluation> evaluations = getEvaluations();
		double attractiveness = 0;
		int n = evaluations.size();
		if (n > 0) {
			for (Evaluation e : evaluations) {
				attractiveness += e.getAttractiveness();
			}
			attractiveness /= n;
		}
		return attractiveness;
		
	}
	
	public double getRisk() {
		List<Evaluation> evaluations = getEvaluations();
		double risk = 0;
		int n = evaluations.size();
		if (n > 0) {
			for (Evaluation e : evaluations) {
				risk += e.getRiskLevel();
			}
			risk /= n;
		}
		return risk;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) throws InvalidDataException {
		if(acronym == null || acronym.trim().equals("")) {
			throw new InvalidDataException("Acronym is mandatory");
		}
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws InvalidDataException {
		if(description == null || description.trim().equals("")) {
			throw new InvalidDataException("Description is mandatory");
		}		
		this.description = description;
	}

	public int getFundingDuration() {
		return fundingDuration;
	}

	public void setFundingDuration(int fundingDuration) throws InvalidDataException {
		if(fundingDuration <= 0) {
			throw new InvalidDataException("Funding must be specified, input only the numbers please.");
		}				
		this.fundingDuration = fundingDuration;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) throws InvalidDataException {
		if(budget <= 0) {
			throw new InvalidDataException("Budget must be specified, input only the numbers please.");
		}				
		this.budget = budget;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) throws InvalidDataException {
		if(owner == null) {
			throw new InvalidDataException("Project must have an owner");
		}				
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) throws InvalidDataException {
		if(category == null) {
			throw new InvalidDataException("Project must have a category");
		}				
		this.category = category;
	}

	public void addEvaluation(Evaluation eval) {
		eval.setProject(this);
		EvaluationDB.saveEvaluation(eval);
	}

	public List<Evaluation> getEvaluations() {
		List<Evaluation> evaluations = null;
		try {
			evaluations = EvaluationDB.getEvaluationsOfProject(this);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		return evaluations;
	}
	
	public void addDocument(Document doc) {
		doc.setProject(this);
		DocumentDB.saveDocument(doc);
	}
	
	public List<Document> getDocuments() {
		List<Document> documents = null;
		try {
			documents = DocumentDB.getDocumentsOfProject(this);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		return documents;
	}
	



}
