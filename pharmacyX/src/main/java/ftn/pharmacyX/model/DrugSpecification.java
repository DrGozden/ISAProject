package ftn.pharmacyX.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ftn.pharmacyX.enums.DrugForm;
import ftn.pharmacyX.enums.DrugType;

@Entity
public class DrugSpecification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated
	private DrugType drugType;
	@Column
	private String contraindications;
	@Column
	private String structure;
	@Enumerated
	private DrugForm drugForm;
	@Column
	private String dailyRecommendation;
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( 
			  joinColumns = @JoinColumn(name = "drug_specification_id"), 
			  inverseJoinColumns = @JoinColumn(name = "substitute_drug_id"))
	private List<Drug> substitutes;
	@Column
	private String producer;
	@Column
	private String description;
	@Column
	private boolean prescription;
	@Column
	private boolean deleted = false;
	
	public DrugSpecification() {
		
	}

	public DrugSpecification(Long id, DrugType drugType, String contraindications, String structure, DrugForm drugForm,
			String dailyRecommendation, List<Drug> substitutes, String producer, String description,
			boolean prescription, boolean deleted) {
		super();
		this.id = id;
		this.drugType = drugType;
		this.contraindications = contraindications;
		this.structure = structure;
		this.drugForm = drugForm;
		this.dailyRecommendation = dailyRecommendation;
		this.substitutes = substitutes;
		this.producer = producer;
		this.description = description;
		this.prescription = prescription;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DrugType getDrugType() {
		return drugType;
	}

	public void setDrugType(DrugType drugType) {
		this.drugType = drugType;
	}

	public String getContraindications() {
		return contraindications;
	}

	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public DrugForm getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(DrugForm drugForm) {
		this.drugForm = drugForm;
	}

	public String getDailyRecommendation() {
		return dailyRecommendation;
	}

	public void setDailyRecommendation(String dailyRecommendation) {
		this.dailyRecommendation = dailyRecommendation;
	}

	public List<Drug> getSubstitutes() {
		return substitutes;
	}

	public void setSubstitutes(List<Drug> substitutes) {
		this.substitutes = substitutes;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrescription() {
		return prescription;
	}

	public void setPrescription(boolean prescription) {
		this.prescription = prescription;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
