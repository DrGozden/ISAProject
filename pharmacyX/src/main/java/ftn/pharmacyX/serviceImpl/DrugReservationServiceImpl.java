package ftn.pharmacyX.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.DrugReservation;
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

	
}
