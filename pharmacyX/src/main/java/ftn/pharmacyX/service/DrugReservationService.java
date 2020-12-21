package ftn.pharmacyX.service;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.model.DrugReservation;

public interface DrugReservationService {

	public DrugReservation makeReservation(DrugReservationDTO reservationDTO);
	public DrugReservation cancelReservation(Long id);
}
