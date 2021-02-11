package ftn.pharmacyX.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.CreateExamDTO;
import ftn.pharmacyX.dto.DermatologistExamDTO;
import ftn.pharmacyX.dto.NewConsultationDTO;
import ftn.pharmacyX.dto.PharmacistConsultationDTO;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.WorkingHours;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.Pharmacist;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.AppointmentRepository;
import ftn.pharmacyX.service.AppointmentService;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.PriceListService;
import ftn.pharmacyX.service.UserService;
import ftn.pharmacyX.service.VacationService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private PriceListService priceListService;
	
	@Autowired
	private DTOConverter converter;
	
	@Autowired
	private VacationService vacationService;


	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
	public PharmacistConsultation scheduleConsultation(User patient, NewConsultationDTO consultationDTO) {
		PharmacistConsultation consultation = new PharmacistConsultation();
		consultation.setDateTime(LocalDateTime.parse(consultationDTO.getDateTime(), formatter1));
		consultation.setPharmacist((Pharmacist) userService.findById(consultationDTO.getPharmacistId()));
		consultation.setPharmacy(pharmacyService.getPharmacy(consultationDTO.getPharmacyId()));
		consultation.setPatient((Patient) patient);
		List<WorkingHours> workingHours = consultation.getPharmacist().getWorkingHours();
		// ako je na godisnjem odmoru
		if (vacationService.isEmployeeOnVacation(consultation.getPharmacist().getId(), consultation.getDateTime())) {
			return null;
		}
		boolean flag = false;
		// ako pokusava da napravi exam koji nije u intervalu radnog vremena dermatologa
		for (WorkingHours wh : workingHours) {
			// working hours u datoj apoteci
			if (wh.getPharmacyId().equals(consultationDTO.getPharmacyId())) {
				// uslov da je u intervalu radnog vremena u toj apoteci
				if (wh.getDay().name().equalsIgnoreCase(consultation.getDateTime().getDayOfWeek().name())){
					if (consultation.getDateTime().toLocalTime().isBefore(wh.getEndTime()) &&
							consultation.getDateTime().toLocalTime().isAfter(wh.getStartTime())) {
						flag = true;
						break;
					}
				}
			}
		}

		if (flag) {
			List<PharmacistConsultationDTO> consultations = getPharmacistConsutationsForPharmacy(consultation.getPharmacy().getId());
			for (PharmacistConsultationDTO phd: consultations) {
				if(phd.getPharmacistId() == consultation.getPharmacist().getId()){
					if(phd.getDateTime().equals(consultation.getDateTime())){
						return null;
					}
				}
			}
			return appointmentRepo.save(consultation);
		}
		return null;



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
	public List<DermatologistExamDTO> getDermatologistExamsForPharmacy(Long pharmacyId) {
		List<DermatologistExam> exams = appointmentRepo.findDermatologistExamsByPharmacyId(pharmacyId);
		List<DermatologistExamDTO> examsDTO = new ArrayList<>();
		for (DermatologistExam exam: exams) {
			examsDTO.add(new DermatologistExamDTO(exam));
		}
		return examsDTO;
	}

	@Override
	public List<PharmacistConsultationDTO> getPharmacistConsutationsForPharmacy(Long pharmacyId) {
		List<PharmacistConsultation> consultations = appointmentRepo.findConsultationsByPharmacyId(pharmacyId);
		List<PharmacistConsultationDTO> dtos = new ArrayList<>();
		for (PharmacistConsultation consultation : consultations) {
			dtos.add(new PharmacistConsultationDTO(consultation));
		}
		return dtos;
	}

	@Override
	public List<PharmacistConsultationDTO> getPharmacistConsutationsForUser(Patient patient) {
		List<PharmacistConsultation> consultations = appointmentRepo.findConsultationsByUserId(patient.getId());
		List<PharmacistConsultationDTO> dtos = new ArrayList<>();
		for (PharmacistConsultation consultation : consultations) {
			dtos.add(new PharmacistConsultationDTO(consultation));
		}
		return dtos;
	}

	@Override
	public List<DermatologistExamDTO> getDermatologistExamsForUser(Patient patient) {
		List<DermatologistExam> exams = appointmentRepo.findExamsByUserId(patient.getId());
		List<DermatologistExamDTO> examsDTO = new ArrayList<>();
		for (DermatologistExam exam: exams) {
			examsDTO.add(new DermatologistExamDTO(exam));
		}
		return examsDTO;
	}

	@Override
	public List<DermatologistExam> getUnreservedDermatologistExamsForPharmacy(Long pharmacyId) {
		List<DermatologistExam> exams = appointmentRepo.findDermatologistExamsByPharmacyId(pharmacyId);
		List<DermatologistExam> freeExams = new ArrayList<DermatologistExam>();
		for (DermatologistExam dermatologistExam : exams) {
			System.out.println(dermatologistExam.getId());
			if(dermatologistExam.getDateTime().isAfter(LocalDateTime.now()) && dermatologistExam.getPatient()==null) {
				System.out.println(dermatologistExam.getPatient());
				freeExams.add(dermatologistExam);	
			}
		}
		return freeExams;
	}

	@Override
	public List<PharmacistConsultation> getUnreservedConsultationsForPharmacy(Long pharmacyId) {
		List<PharmacistConsultation> consultations = appointmentRepo.findConsultationsByPharmacyId(pharmacyId);
		List<PharmacistConsultation> freeConsultations = new ArrayList<PharmacistConsultation>();
		for (PharmacistConsultation consultation : consultations) {
			if(consultation.getDateTime().isAfter(LocalDateTime.now())) {
				freeConsultations.add(consultation);	
			}
		}
		return freeConsultations;
	}
	

	@Override
	public DermatologistExam createExam(CreateExamDTO dto) {
		DermatologistExam exam = converter.dtoToExam(dto);
		List<WorkingHours> workingHours = exam.getDermatologist().getWorkingHours();
		// ako je na godisnjem odmoru
		if (vacationService.isEmployeeOnVacation(dto.getDermatologistId(), exam.getDateTime())) {
			return null;
		}
		boolean flag = false;
		// ako pokusava da napravi exam koji nije u intervalu radnog vremena dermatologa
		for (WorkingHours wh : workingHours) {
			// working hours u datoj apoteci
			if (wh.getPharmacyId().equals(dto.getPharmacyId())) {
				// uslov da je u intervalu radnog vremena u toj apoteci
				if (wh.getDay().name().equalsIgnoreCase(exam.getDateTime().getDayOfWeek().name())){
					if (exam.getDateTime().toLocalTime().isBefore(wh.getEndTime()) &&
							exam.getDateTime().toLocalTime().isAfter(wh.getStartTime())) {
						flag = true;
						break;
					}
				}
			}
		}
		if (flag) {
			return appointmentRepo.save(exam);
		}
		return null;
	}


}
