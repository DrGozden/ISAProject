package ftn.pharmacyX.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.enums.UserStatus;
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

	public PharmacyAdmin(UserDTO dto) {
		this.setFirstName(dto.getFirstName());
		this.setLastName(dto.getLastName());
		this.setEmail(dto.getEmail());
		this.setPassword("admin");
		this.setUserRole(UserRole.PHARMACY_ADMIN);
		this.setUserStatus(UserStatus.FIRST_LOGIN);
		this.setPhone(dto.getPhone());
		this.setDeleted(false);
		this.setAddress(dto.getAddress());
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	
	
}
