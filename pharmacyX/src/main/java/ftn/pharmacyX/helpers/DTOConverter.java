package ftn.pharmacyX.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.repository.DrugRepository;
import ftn.pharmacyX.repository.PharmacyRepository;

@Service
public class DTOConverter {
	
	@Autowired
	private DrugRepository drugRepo;
	
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
	
	
}
