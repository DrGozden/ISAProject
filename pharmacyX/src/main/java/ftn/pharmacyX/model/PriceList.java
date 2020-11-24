package ftn.pharmacyX.model;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private LocalDate startDate;
	@Column
	private LocalDate endDate;
	@ElementCollection
	private Map<Drug, Double> prices;
	@Column
	private boolean deleted = false;
	
	public PriceList() {
		
	}

	public PriceList(Long id, LocalDate startDate, LocalDate endDate, Map<Drug, Double> prices, boolean deleted) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.prices = prices;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Map<Drug, Double> getPrices() {
		return prices;
	}

	public void setPrices(Map<Drug, Double> prices) {
		this.prices = prices;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
}
