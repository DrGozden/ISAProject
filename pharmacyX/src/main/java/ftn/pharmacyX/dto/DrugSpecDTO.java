package ftn.pharmacyX.dto;

import java.util.List;

import ftn.pharmacyX.enums.DrugForm;
import ftn.pharmacyX.enums.DrugType;

public class DrugSpecDTO {

	private DrugType drugType;
	private String contraindications;
	private String structure;
	private DrugForm drugForm;
	private String dailyRecommendation;
	private List<Integer> subtitues;
	private String producer;
	private String description;
	private boolean perscription;
	
	public DrugSpecDTO() {
		
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

	public List<Integer> getSubtitues() {
		return subtitues;
	}

	public void setSubtitues(List<Integer> subtitues) {
		this.subtitues = subtitues;
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

	public boolean isPerscription() {
		return perscription;
	}

	public void setPerscription(boolean perscription) {
		this.perscription = perscription;
	}
	
	
}
