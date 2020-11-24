package ftn.pharmacyX.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Pharmacist;

@Entity
public class Pharmacy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Dermatologist> dermatologists;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Pharmacist> pharmacists;
	@ElementCollection
	private List<Integer> ratings;
	@OneToMany
	private List<PriceList> priceList;
	@ElementCollection
	private Map<Drug, Integer> drugsInStock;
	@Column
	private boolean deleted = false;

	public Pharmacy() {

	}

	public Pharmacy(Long id, String name, String description, List<Dermatologist> dermatologists,
			List<Pharmacist> pharmacists, List<Integer> ratings, List<PriceList> priceList,
			Map<Drug, Integer> drugsInStock, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dermatologists = dermatologists;
		this.pharmacists = pharmacists;
		this.ratings = ratings;
		this.priceList = priceList;
		this.drugsInStock = drugsInStock;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(List<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public List<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(List<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(List<Integer> ratings) {
		this.ratings = ratings;
	}

	public List<PriceList> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<PriceList> priceList) {
		this.priceList = priceList;
	}

	public Map<Drug, Integer> getDrugsInStock() {
		return drugsInStock;
	}

	public void setDrugsInStock(Map<Drug, Integer> drugsInStock) {
		this.drugsInStock = drugsInStock;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
