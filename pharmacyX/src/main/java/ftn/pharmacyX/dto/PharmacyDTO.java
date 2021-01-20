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
	
	public PharmacyDTO() {
		
	}

	public PharmacyDTO(Long id, String name, String description, Address address, List<Pharmacist> pharmacists,
			List<Dermatologist> dermatologists, List<PriceList> priceList, List<DrugsInStockDTO> drugsInStock) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.pharmacists = pharmacists;
		this.dermatologists = dermatologists;
		this.priceList = priceList;
		this.drugsInStock = drugsInStock;
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
	
	
	
}