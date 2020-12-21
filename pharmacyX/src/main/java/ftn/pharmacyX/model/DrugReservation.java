package ftn.pharmacyX.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DrugReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Drug drug;
	@ManyToOne(fetch = FetchType.LAZY)
	private Pharmacy pharmacy;
	@Column
	private LocalDateTime deadline;
	@Column
	private String code;
	@Column
	private boolean deleted = false;
	
	public DrugReservation() {
		this.code = UUID.randomUUID().toString();
	}

	public DrugReservation(Long id, Drug drug, Pharmacy pharmacy, LocalDateTime deadline,
			boolean deleted) {
		super();
		this.id = id;
		this.drug = drug;
		this.pharmacy = pharmacy;
		this.deadline = deadline;
		this.code = UUID.randomUUID().toString();
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
