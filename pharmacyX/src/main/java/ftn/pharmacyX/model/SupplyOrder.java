	package ftn.pharmacyX.model;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SupplyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ElementCollection
	@CollectionTable(name = "drug_supplies_mapping", joinColumns = {
			@JoinColumn(name = "order_id", referencedColumnName = "id")})
	@MapKeyJoinColumn(name = "drug_id")
	@Column(name = "quantity")
	private Map<Drug, Integer> supplies;
	@Column
	private LocalDateTime deadline;
	@OneToOne
	private Pharmacy pharmacy;
	@Column
	private boolean deleted = false;
	
	public SupplyOrder() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Drug, Integer> getSupplies() {
		return supplies;
	}

	public void setSupplies(Map<Drug, Integer> supplies) {
		this.supplies = supplies;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
