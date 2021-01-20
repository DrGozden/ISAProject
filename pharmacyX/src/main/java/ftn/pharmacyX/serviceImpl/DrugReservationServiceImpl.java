package ftn.pharmacyX.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.DrugReservationRepository;
import ftn.pharmacyX.service.DrugReservationService;

public class DrugReservationServiceImpl implements DrugReservationService {

	@Autowired
	private DTOConverter converter;
	
	@Autowired
	private DrugReservationRepository reservationRepo;
	
	@Override
	public DrugReservation makeReservation(DrugReservationDTO reservationDTO) {
		DrugReservation newReservation = converter.dtoToDrugReservation(reservationDTO);	
		return reservationRepo.save(newReservation);
		
	}

	@Override
	public DrugReservation cancelReservation(Long id) {
		DrugReservation forCancelation = reservationRepo.getOne(id);
		LocalDateTime now = LocalDateTime.now();
		if (now.isBefore(forCancelation.getDeadline().minusHours(24))) {
			forCancelation.setDeleted(true);
		}
		return forCancelation;
	}

	@Override
	public List<DrugReservation> getDrugReservationsForUser(User user) {
		List<DrugReservation> reservations = null;
		Patient patient = (Patient) user;
		for (DrugReservation drugReservation : patient.getDrugReservations()) {
			reservations.add(drugReservation);
		}
		return reservations;
	}
	
	
	

	
}
