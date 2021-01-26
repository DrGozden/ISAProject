package ftn.pharmacyX.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pharmacy_admin_user")
public class PharmacyAdmin extends User {

}
