package ftn.pharmacyX.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dermatologist_user")
public class Dermatologist extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dermatologist() {
		
	}
	
	
}
