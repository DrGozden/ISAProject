package ftn.pharmacyX.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.Vacation;
import ftn.pharmacyX.repository.VacationRepository;
import ftn.pharmacyX.service.VacationService;

@Service
public class VacationServiceImpl implements VacationService {

	@Autowired
	private VacationRepository vacationRepository;
	
	@Override
	public boolean approveVacation(Long id) {
		Vacation v = vacationRepository.findByIdAndDeleted(id, false).orElse(null);
		if (v == null) {
			return false;
		}
		v.setAccepted(true);
		vacationRepository.save(v);
		// SEND EMAIL
		return true;
	}
	
	@Override
	public boolean rejectVacation(Long id, String description) {
		Vacation v = vacationRepository.findByIdAndDeleted(id, false).orElse(null);
		if (v == null) {
			return false;
		}
		
		v.setAccepted(false);
		v.setRejectDescription(description);
		v.setDeleted(true);
		vacationRepository.save(v);
		
		// SEND EMAIL
		return true;
	}

	@Override
	public List<Vacation> getVacationRequests() {
		return vacationRepository.findAllByDeleted(false);
	}

	@Override
	public boolean isEmployeeOnVacation(Long employeeId) {
		List<Vacation> allVacations = getVacationRequests();
		LocalDate now = LocalDate.now();
		for (Vacation v : allVacations) {
			if (v.getUserId().equals(employeeId) &&
				now.isAfter(v.getStartDate()) &&
				now.isBefore(v.getEndDate())) {
				return true;
			}
		}
		return false;
	}

	
}
