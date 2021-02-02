package ftn.pharmacyX.dto;

import java.time.LocalDate;
import java.util.Map;

import ftn.pharmacyX.model.Drug;

public class PriceListDTO {

	private LocalDate startDate;
	private Map<Drug, Double> prices;

	public PriceListDTO() {
	}

	public PriceListDTO(LocalDate startDate, Map<Drug, Double> prices) {
		super();
		this.startDate = startDate;
		this.prices = prices;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Map<Drug, Double> getPrices() {
		return prices;
	}

	public void setPrices(Map<Drug, Double> prices) {
		this.prices = prices;
	}

}
