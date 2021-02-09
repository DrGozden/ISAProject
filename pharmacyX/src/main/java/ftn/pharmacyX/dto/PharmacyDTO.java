package ftn.pharmacyX.dto;

import java.util.List;

import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Pharmacist;

public class PharmacyDTO {

	private Long id;
	private String name;
	private String description;
	private Address address;
	private List<Pharmacist> pharmacists;
	private List<Dermatologist> dermatologists;
	private List<PriceList> priceList;
	private List<DrugsInStockDTO> drugsInStock;
	private double rating;
	private List<PriceListDTO> priceListsDTO;

	public PharmacyDTO() {

	}

	public PharmacyDTO(Long id, String name, String description, Address address, List<Pharmacist> pharmacists,
			List<Dermatologist> dermatologists, List<PriceList> priceList, List<DrugsInStockDTO> drugsInStock,
			double rating, List<PriceListDTO> priceListsDTO) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.pharmacists = pharmacists;
		this.dermatologists = dermatologists;
		this.priceList = priceList;
		this.drugsInStock = drugsInStock;
		this.rating = rating;
		this.priceListsDTO = priceListsDTO;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(List<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	public List<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(List<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public List<PriceList> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<PriceList> priceList) {
		this.priceList = priceList;
	}

	public List<DrugsInStockDTO> getDrugsInStock() {
		return drugsInStock;
	}

	public void setDrugsInStock(List<DrugsInStockDTO> drugsInStock) {
		this.drugsInStock = drugsInStock;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(List<Integer> ratings) {
		for (int rate : ratings) {
			rating = rating + rate;
		}
		if (ratings.size() == 0) {
			rating = 0;
		} else {
			rating = rating / ratings.size();
		}
	}

	public List<PriceListDTO> getPriceListsDTO() {
		return priceListsDTO;
	}

	public void setPriceListsDTO(List<PriceListDTO> priceListsDTO) {
		this.priceListsDTO = priceListsDTO;
	}

}
