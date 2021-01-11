package ftn.pharmacyX.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.repository.DrugRepository;
import ftn.pharmacyX.repository.PharmacyRepository;

@Service
public class DTOConverter {
	
	@Autowired
	private DrugRepository drugRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	@Autowired
	private PharmacyRepository pharmacyRepo;

	public DrugReservation dtoToDrugReservation(DrugReservationDTO dto) {
		DrugReservation reservation = new DrugReservation();
		reservation.setDrug(drugRepo.findById(dto.getDrugId()).orElse(null));
		reservation.setPharmacy(pharmacyRepo.getOne(dto.getPharmacyId()));
		reservation.setDeadline(LocalDateTime.parse(dto.getDeadlineDateTime(), formatter));
		return reservation;
	}
	
	public Patient userDtoToPatient(UserDTO dto) {
		Patient p = new Patient();
		p.setEmail(dto.getEmail());
		p.setPassword(passwordEncoder.encode(dto.getPassword()));
		p.setFirstName(dto.getFirstName());
		p.setLastName(dto.getLastName());
		Address address = new Address();
		address.setCity(dto.getAddress().getCity());
		address.setCountry(dto.getAddress().getCountry());
		address.setDeleted(false);
		address.setPostalCode(dto.getAddress().getPostalCode());
		address.setStreet(dto.getAddress().getStreet());
		p.setAddress(address);
		p.setPhone(dto.getPhone());
		p.setUserRole(UserRole.PATIENT);
		return p;
	}
	
	
}
