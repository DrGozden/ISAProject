package ftn.pharmacyX.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.CreateExamDTO;
import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.dto.DrugsInStockDTO;
import ftn.pharmacyX.dto.PharmacyDTO;
import ftn.pharmacyX.dto.PriceListDTO;
import ftn.pharmacyX.dto.SupplyOrderDTO;
import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.dto.VacationWithUserDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.model.SupplyOrder;
import ftn.pharmacyX.model.Vacation;
import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.DrugRepository;
import ftn.pharmacyX.repository.PharmacyRepository;
import ftn.pharmacyX.service.DrugService;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.SupplyService;
import ftn.pharmacyX.service.UserService;

@Service
public class DTOConverter {
	
	@Autowired
	private DrugRepository drugRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private SupplyService supplyService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
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
	
	public PharmacyDTO pharmacyToDTO(Pharmacy pharmacy) {
		PharmacyDTO dto = new PharmacyDTO();
		dto.setId(pharmacy.getId());
		dto.setAddress(pharmacy.getAddress());
		dto.setDermatologists(pharmacy.getDermatologists());
		dto.setDescription(pharmacy.getDescription());
		dto.setName(pharmacy.getName());
		dto.setPharmacists(pharmacy.getPharmacists());
		dto.setPriceList(pharmacy.getPriceList());
		dto.setDrugsInStock(inStockToDTO(pharmacy.getDrugsInStock()));
		dto.setRating(pharmacy.getRatings());
		List<PriceListDTO> priceListDTO= new ArrayList<>();
		for (PriceList priceList : pharmacy.getPriceList()) {
			priceListDTO.add(new PriceListDTO(priceList));
		}
		dto.setPriceListsDTO(priceListDTO);
		return dto;
	}
	
	public List<DrugsInStockDTO> inStockToDTO(Map<Drug, Integer> inStock) {
		List<DrugsInStockDTO> retList = new ArrayList<DrugsInStockDTO>();
		for (Drug d : inStock.keySet()) {
			retList.add(new DrugsInStockDTO(d, inStock.get(d)));
		}
		return retList;
	}
	
	public List<PharmacyDTO> pharmaciesToDTO(List<Pharmacy> pharmacies) {
		List<PharmacyDTO> dtos = new ArrayList<PharmacyDTO>();
		for (Pharmacy pharmacy : pharmacies) {
			dtos.add(pharmacyToDTO(pharmacy));
		}
		
		return dtos;
	}
	
	public DermatologistExam dtoToExam(CreateExamDTO dto) {
		DermatologistExam exam = new DermatologistExam();
		exam.setPatient(null);
		exam.setPrice(dto.getPrice());
		exam.setDateTime(LocalDateTime.parse(dto.getDateStart(), formatter));
		Dermatologist d = (Dermatologist) userService.findById(dto.getDermatologistId());
		exam.setDermatologist(d);
		Pharmacy ph = pharmacyService.getPharmacy(dto.getPharmacyId());
		exam.setPharmacy(ph);
		return exam;
	}
	
	public SupplyOrder dtoToOrder(SupplyOrderDTO dto) {
		SupplyOrder order = new SupplyOrder();
		order.setDeadline(LocalDateTime.parse(dto.getDeadline(), formatter));
		Pharmacy ph = pharmacyService.getPharmacy(dto.getPharmacyId());
		if (ph == null) {
			return null;
		}
		order.setPharmacy(ph);
		Map<Drug, Integer> supplies = new HashMap<Drug, Integer>();
		for (Long drugId : dto.getSupplies().keySet()) {
			Drug drug = drugService.getDrug(drugId);
			if (drug == null) {
				return null;
			}
			supplies.put(drug, supplies.get(drugId));
		}
		
		order.setSupplies(supplies);
		return order;
	}
	
	public VacationWithUserDTO vacationToDto(Vacation v) {
		VacationWithUserDTO dto = new VacationWithUserDTO();
		dto.setVacation(v);
		User u = userService.findById(dto.getVacation().getUserId());
		if (u == null) {
			return null;
		}
		
		dto.setEmail(u.getEmail());
		dto.setFirstName(u.getFirstName());
		dto.setLastName(u.getLastName());
		
		return dto;
	}
	
}
