package ftn.pharmacyX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.model.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUuid(String uuid);
	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	List<User> findAllByUserRole(UserRole userRole);
	
}
