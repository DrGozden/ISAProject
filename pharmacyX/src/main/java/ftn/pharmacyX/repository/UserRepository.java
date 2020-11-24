package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.users.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
