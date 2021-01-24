package ftn.pharmacyX.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.DrugReservationRepository;
import ftn.pharmacyX.service.DrugReservationService;

@Service
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
	public List<DrugReservationDTO> getDrugReservationsForUser(User user) {
		List<DrugReservation> reservations = null;
		Patient patient = (Patient) user;
		List<DrugReservationDTO> dtos = new ArrayList<>();
		for (DrugReservation drugReservation : patient.getDrugReservations()) {
			dtos.add(new DrugReservationDTO(drugReservation));
		}
		return dtos;
	}
	
	
	

	
}
