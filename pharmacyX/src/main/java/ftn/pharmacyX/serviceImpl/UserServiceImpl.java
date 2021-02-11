package ftn.pharmacyX.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ftn.pharmacyX.model.users.PharmacyAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.enums.UserStatus;
import ftn.pharmacyX.exceptions.EntityNotFoundException;
import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.Pharmacy;
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

	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		user.setUserStatus(UserStatus.PENDING);
		Address address = addressRepository.save(user.getAddress());
		user.setAddress(address);
		User saved = userRepository.save(user);

		emailService.sendMail(user, "Activation link",
				"Please follow the link below to activate your account \nhttp://localhost:9003/users/activate/"
						+ user.getUuid());

		return saved;

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

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}



	@Override
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
			else if(e instanceof EntityNotFoundException) {
				return null; //Vraca null za korisnika koji nije ulogovan kada se pozove getLoggedUser()
			}
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public UserDTO editUser(UserDTO editedUser) {
		User user = this.getLoggedUser();

		user.setFirstName(editedUser.getFirstName());
		user.setLastName(editedUser.getLastName());
		user.setPhone(editedUser.getPhone());

		user.getAddress().setCity(editedUser.getAddress().getCity());
		user.getAddress().setCountry(editedUser.getAddress().getCountry());
		user.getAddress().setStreet(editedUser.getAddress().getStreet());
		user.getAddress().setPostalCode(editedUser.getAddress().getPostalCode());

		Patient patient;
		if (user.getUserRole() == UserRole.PATIENT) {
			patient = (Patient) user;
			patient.getAllergies().clear();

			for (Drug drug : editedUser.getAllergies()) {
				patient.addAllergy(drug);
			}

			userRepository.save(patient);
		} else {
			userRepository.save(user);
		}

		return new UserDTO(user);
	}
	
	public List<User> searchDermatologistsAndPharmacists(Map<String, String> searchParams, List<User> users) {
		
		String searchParam = null;
		String filter = null;
		
		ArrayList<User> searched = new ArrayList<User>();
		
		if (searchParams.get("search") != null) {
			searchParam = searchParams.get("search").toLowerCase();
		}
		
		if (searchParams.get("filter") != null) {
			filter = searchParams.get("filter").toLowerCase();
		}
		
		if (searchParam == null && filter == null) {
			return searched;
		}
		
		if (searchParam != null) {
			for (User user : users) {
				if (user.getFirstName().toLowerCase().contains(searchParam) || 
						user.getLastName().toLowerCase().contains(searchParam)) {
					searched.add(user);
				}	
			}
		}
		
		return searched;
	}
	
	@Override
	public List<User> findAllPharmacists() {
		return userRepository.findAllByUserRole(UserRole.PHARMACIST);
	}
	
	@Override
	public List<User> findAllDermatologists() {
		return userRepository.findAllByUserRole(UserRole.DERMATOLOGIST);
	}
	
	@Override
	public List<User> findAllDermatologistsForPharmacy(Pharmacy pharmacy) {
		List<User> allDermatologists = findAllDermatologists();
		List<User> ret = new ArrayList<User>();
		
		for (User user : pharmacy.getDermatologists()) {
			for (User user2 : allDermatologists) {
				if (user.getId() == user2.getId()) {
					ret.add(user2);
				}
			}
		}
		
		return ret;
		
	}

	@Override
	public User addDrugReservation(DrugReservation drugReservation) {
		Patient patient = (Patient) getLoggedUser();
		patient.getDrugReservations().add(drugReservation);
		userRepository.save(patient);
		return patient;
	}

	@Override
	public User changePassword(UserDTO userDTO) {
		User current = getLoggedUser();
		current.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return userRepository.save(current);
	}

	@Override
	public PharmacyAdmin createPharmacyAdmin(UserDTO dto) {
		PharmacyAdmin admin = new PharmacyAdmin(dto);
		return userRepository.save(admin);
	}

	@Override
	public User activateUser(User user) {
		user.setUserStatus(UserStatus.ACTIVATED);
		User saved = userRepository.save(user);
		return saved;

	}
}
