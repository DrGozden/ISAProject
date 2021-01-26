package ftn.pharmacyX.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ftn.pharmacyX.enums.OfferStatus;
import ftn.pharmacyX.model.users.Supplier;

@Entity
public class SupplierOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated
	private OfferStatus status;
	@Column
	private double totalPrice;
	@Column
	private LocalDateTime deliveryDeadline;
	@ManyToOne
	private Supplier supplier;
	@ManyToOne
	private SupplyOrder order;
	@Column
	private boolean deleted = false;
	
	public SupplierOffer() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getDeliveryDeadline() {
		return deliveryDeadline;
	}

	public void setDeliveryDeadline(LocalDateTime deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplyOrder getOrder() {
		return order;
	}

	public void setOrder(SupplyOrder order) {
		this.order = order;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
