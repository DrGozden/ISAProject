package ftn.pharmacyX.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ftn.pharmacyX.model.Pharmacy;

@Entity
@DiscriminatorValue("pharmacy_admin_user")
public class PharmacyAdmin extends User {
	
	
	@OneToOne
	private Pharmacy pharmacy;
	
	public PharmacyAdmin() {
		
	}


	public PharmacyAdmin( Pharmacy pharmacy) {
		super();

		this.pharmacy = pharmacy;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	
	
}
