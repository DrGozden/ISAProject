package ftn.pharmacyX.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.AppointmentRepository;
import ftn.pharmacyX.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepo;

	@Override
	public DermatologistExam scheduleExam(User patient, Long examId) {
		DermatologistExam exam = (DermatologistExam) appointmentRepo.findById(examId).orElse(null);
		
		if (exam == null) {
			return null;
		}
		
		exam.setPatient((Patient) patient);
		return appointmentRepo.save(exam);

	}

	@Override
	public DermatologistExam cancelExam(Long examId) {
		DermatologistExam exam = (DermatologistExam) appointmentRepo.findById(examId).orElse(null);
		
		if (exam == null) {
			return null;
		}
		
		exam.setPatient(null);
		return appointmentRepo.save(exam);
		
	}

	@Override
	public PharmacistConsultation scheduleConsultation(User patient, Long consultationId) {
		PharmacistConsultation consultation = (PharmacistConsultation) appointmentRepo.findById(consultationId).orElse(null);
		
		if (consultation == null) {
			return null;
		}
		
		consultation.setPatient((Patient) patient);
		return appointmentRepo.save(consultation);
	}

	@Override
	public PharmacistConsultation cancelConsultation(Long consultationId) {
		PharmacistConsultation consultation = (PharmacistConsultation) appointmentRepo.findById(consultationId).orElse(null);
		
		if (consultation == null) {
			return null;
		}
		consultation.setPatient(null);
		return appointmentRepo.save(consultation);
	}

	@Override
	public List<DermatologistExam> getDermatologistExamsForPharmacy(Long pharmacyId) {
		return appointmentRepo.findDermatologistExamsByPharmacyId(pharmacyId);
	}

	@Override
	public List<PharmacistConsultation> getPharmacistConsutationsForPharmacy(Long pharmacyId) {
		return appointmentRepo.findConsultationsByPharmacyId(pharmacyId);
	}


}
