package ftn.pharmacyX.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("supplier_user")
public class Supplier extends User {

}
