package ftn.pharmacyX.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ftn.pharmacyX.dto.PharmacyDTO;
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
	@OneToOne
	private Address address;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( 
			  joinColumns = @JoinColumn(name = "pharmacy_id"), 
			  inverseJoinColumns = @JoinColumn(name = "dermatologist_id"))
	private List<Dermatologist> dermatologists;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( 
			  joinColumns = @JoinColumn(name = "pharmacy_id"), 
			  inverseJoinColumns = @JoinColumn(name = "pharmacist_id"))
	private List<Pharmacist> pharmacists;
	@ElementCollection
	private List<Integer> ratings;
	@OneToMany
	private List<PriceList> priceList;
	@ElementCollection
	@CollectionTable(name = "pharmacy_stock_mapping", joinColumns = {
			@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")})
	@MapKeyJoinColumn(name = "drug_id")
	@Column(name = "quantity")
	private Map<Drug, Integer> drugsInStock;
	@Column
	private boolean deleted = false;

	public Pharmacy() {

	}

	public Pharmacy(Long id, String name, String description, Address address, List<Dermatologist> dermatologists,
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
		this.address = address;
	}

    public Pharmacy(PharmacyDTO dto) {
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.dermatologists = new ArrayList<>();
		this.pharmacists =  new ArrayList<>();
		this.ratings = new ArrayList<>();
		this.priceList = new ArrayList<>();
		this.drugsInStock = new HashMap<Drug, Integer>();
		this.address = dto.getAddress();
		this.deleted = false;


    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
