package ftn.pharmacyX.service;

import java.util.List;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.users.User;

public interface DrugReservationService {

	public DrugReservation makeReservation(DrugReservationDTO reservationDTO);
	public DrugReservation cancelReservation(Long id);
	public List<DrugReservation> getDrugReservationsForUser(User user);
}
