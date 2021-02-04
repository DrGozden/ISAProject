package ftn.pharmacyX.service;

import java.time.LocalDateTime;
import java.util.List;

import ftn.pharmacyX.model.Vacation;

public interface VacationService {

	public boolean approveVacation(Long id);
	public boolean rejectVacation(Long id, String description);
	public List<Vacation> getVacationRequests();
	boolean isEmployeeOnVacation(Long employeeId);
	boolean isEmployeeOnVacation(Long employeeId, LocalDateTime dateTime);
}
