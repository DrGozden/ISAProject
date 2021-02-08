package ftn.pharmacyX.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.PriceList;

public class PriceListDTO {

	private LocalDate startDate;
	private String startDateString;
	//private Map<Drug, Double> prices;
	private List<Drug> drugs;
	private List<Double> pricesList;

	public PriceListDTO() {
	}

	public PriceListDTO(LocalDate startDate, String startDateString, List<Drug> drugs,
			List<Double> pricesList) {
		super();
		this.startDate = startDate;
		this.startDateString = startDateString;
		//this.prices = prices;
		this.drugs = drugs;
		this.pricesList = pricesList;
	}

	public PriceListDTO(PriceList priceList) {
		this.startDate = priceList.getStartDate();
		//this.prices = priceList.getPrices();
		this.drugs = new ArrayList<>();
		this.pricesList = new ArrayList<>();
		for (Drug drug : priceList.getPrices().keySet()) {
			this.drugs.add(drug);
			this.pricesList.add(priceList.getPrices().get(drug));
		}
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
/*
	public Map<Drug, Double> getPrices() {
		return prices;
	}

	public void setPrices(Map<Drug, Double> prices) {
		this.prices = prices;
	}
*/
	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public List<Double> getPricesList() {
		return pricesList;
	}

	public void setPricesList(List<Double> pricesList) {
		this.pricesList = pricesList;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

}
