package ftn.pharmacyX.service;

import java.util.List;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;

public interface AppointmentService {

	public DermatologistExam scheduleExam(User patient, Long examId);
	public DermatologistExam cancelExam(Long examId);	
	
	public PharmacistConsultation scheduleConsultation(User patient, Long consultationId);
	public PharmacistConsultation cancelConsultation(Long consultationId);
	
	public List<DermatologistExam> getDermatologistExamsForPharmacy(Long pharmacyId);
	public List<PharmacistConsultation> getPharmacistConsutationsForPharmacy(Long pharmacyId);
	public List<PharmacistConsultation> getPharmacistConsutationsForUser(Patient patient);
	public List<DermatologistExam> getDermatologistExamsForUser(Patient patient);
	public List<DermatologistExam> getUnreservedDermatologistExamsForPharmacy(Long pharmacyId);
	public List<PharmacistConsultation> getUnreservedConsultationsForPharmacy(Long pharmacyId);

}
