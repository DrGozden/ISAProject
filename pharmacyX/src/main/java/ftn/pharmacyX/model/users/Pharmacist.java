package ftn.pharmacyX.model.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ftn.pharmacyX.dto.EmployeeDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.model.WorkingHours;

@Entity
@DiscriminatorValue("pharmacist_user")
public class Pharmacist extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany
	private List<WorkingHours> workingHours = new ArrayList<WorkingHours>();
	
	public Pharmacist() {
		
	}

	public List<WorkingHours> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHours> workingHours) {
		this.workingHours = workingHours;
	}
	
	public Pharmacist(EmployeeDTO dto) {
		this.setFirstName(dto.getFirstName());
		this.setLastName(dto.getLastName());
		this.setEmail(dto.getEmail());
		this.setPassword(dto.getPassword());
		this.setPhone(dto.getPhone());
		this.setUserRole(UserRole.PHARMACIST);
		this.setDeleted(false);
	}
	
	
}
