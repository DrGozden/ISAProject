package ftn.pharmacyX.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pharmacist_user")
public class Pharmacist extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pharmacist() {
		
	}
}
