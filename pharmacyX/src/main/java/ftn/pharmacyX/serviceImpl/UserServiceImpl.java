package ftn.pharmacyX.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.EditPatientDTO;
import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.exceptions.EntityNotFoundException;
import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.AddressRepository;
import ftn.pharmacyX.repository.DrugRepository;
import ftn.pharmacyX.repository.UserRepository;
import ftn.pharmacyX.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private DrugRepository drugRepository;

	@Override
	public User saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUuid(String uuid) {
		return userRepository.findByUuid(uuid);
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new EntityNotFoundException("User not found in the DB", HttpStatus.BAD_REQUEST);
		}
		return user;
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User getLoggedUser() {
		try {
			return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				return null;
			}
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Patient editPatient(EditPatientDTO editedPatient) {
		Patient loggedPatient = (Patient) this.getLoggedUser();

		loggedPatient.setFirstName(editedPatient.getFirstName());
		loggedPatient.setLastName(editedPatient.getLastName());
		loggedPatient.setPhone(editedPatient.getPhone());

		Address address = loggedPatient.getAddress();
		address.setCity(editedPatient.getCity());
		address.setStreet(editedPatient.getStreet());
		address.setCountry(editedPatient.getCountry());
		address.setPostalCode(editedPatient.getPostalCode());

		// DA LI OVO TREBA UOPSTE ???
		addressRepository.save(address);

		loggedPatient.getAllergies().clear();

		for (Long drugId : editedPatient.getAllergies()) {
			loggedPatient.addAllergy(drugRepository.getOne(drugId));
		}

		return userRepository.save(loggedPatient);

	}

	@Override
	public User editEmployee(User editedEmployee) {
		User user = this.getLoggedUser();

		user.setFirstName(editedEmployee.getFirstName());
		user.setLastName(editedEmployee.getLastName());
		user.setPhone(editedEmployee.getPhone());

		user.getAddress().setCity(editedEmployee.getAddress().getCity());
		user.getAddress().setCountry(editedEmployee.getAddress().getCountry());
		user.getAddress().setStreet(editedEmployee.getAddress().getStreet());
		user.getAddress().setPostalCode(editedEmployee.getAddress().getPostalCode());

		userRepository.save(user);

		return user;
	}

}
