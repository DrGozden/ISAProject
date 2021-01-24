package ftn.pharmacyX.model.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ftn.pharmacyX.model.WorkingHours;

@Entity
@DiscriminatorValue("dermatologist_user")
public class Dermatologist extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany
	private List<WorkingHours> workingHours = new ArrayList<WorkingHours>();

	public Dermatologist() {
		
	}

	public List<WorkingHours> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHours> workingHours) {
		this.workingHours = workingHours;
	}
	
	
}
