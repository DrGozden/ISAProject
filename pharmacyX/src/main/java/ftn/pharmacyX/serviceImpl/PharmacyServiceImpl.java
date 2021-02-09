package ftn.pharmacyX.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ftn.pharmacyX.helpers.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.AddDrugDTO;
import ftn.pharmacyX.dto.DermatologistExamDTO;
import ftn.pharmacyX.dto.EmployeeDTO;
import ftn.pharmacyX.dto.FilterDatePharmacistDTO;
import ftn.pharmacyX.dto.PharmacistConsultationDTO;
import ftn.pharmacyX.dto.PharmacyDTO;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.WorkingHours;
import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Pharmacist;
import ftn.pharmacyX.model.users.PharmacyAdmin;
import ftn.pharmacyX.repository.PharmacyRepository;
import ftn.pharmacyX.repository.UserRepository;
import ftn.pharmacyX.service.AppointmentService;
import ftn.pharmacyX.service.DrugService;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.UserService;


@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepo;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DrugService drugService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DTOConverter dtoConverter;
	
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	@Override
	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepo.findByDeleted(false);
	}

	@Override
	public Pharmacy getPharmacy(long id) {
		return pharmacyRepo.findByIdAndDeletedIsFalse(id);
		
	}
	
	@Override
	public List<Pharmacy> getPharmaciesContainingDrug(Long drugId) {
		List<Pharmacy> all = getAllPharmacies();
		List<Pharmacy> ret = new ArrayList<Pharmacy>();
		for (Pharmacy pharmacy : all) {
			for (Drug drug : pharmacy.getDrugsInStock().keySet()) {
				if (drug.getId().equals(drugId)) {
					ret.add(pharmacy);
					break;
				}
			}
		}
		return ret;
	}

	@Override
	public List<Pharmacist> getAvailablePharmacist(FilterDatePharmacistDTO queryDto) {
		queryDto.setLocalDateTime(LocalDateTime.parse(queryDto.getDateTime(),formatter));
		List<Pharmacist> availablePharmacistTemp = new ArrayList<>();
		Pharmacy pharmacy = getPharmacy(queryDto.getPharmacyId());
		if(queryDto.getLocalDateTime().isAfter(LocalDateTime.now())){
			for(Pharmacist pharmacist: pharmacy.getPharmacists()){
				for(WorkingHours wh: pharmacist.getWorkingHours()){
					if(wh.getDay().name().equalsIgnoreCase(queryDto.getLocalDateTime().getDayOfWeek().name())){
						if(queryDto.getLocalDateTime().getHour() >= (wh.getStartTime().getHour()) && queryDto.getLocalDateTime().getHour() <= (wh.getEndTime().getHour()) ){
							availablePharmacistTemp.add(pharmacist); //Radno vreme im je u periodu koji je zahtevan
						}
					}
				}
			}

			List<Pharmacist> notAvailablePharmacist = new ArrayList<>();//Farmaceuti koje treba izbaciti iz liste dostupnih

			List<PharmacistConsultationDTO> consultations = appointmentService.getPharmacistConsutationsForPharmacy(pharmacy.getId());
			for (PharmacistConsultationDTO consultationsDTO: consultations) {
				for(Pharmacist pharmacist: availablePharmacistTemp){
					if(consultationsDTO.getPharmacistId().equals(pharmacist.getId()) && consultationsDTO.getDateTime().getHour() == queryDto.getLocalDateTime().getHour()
							&& consultationsDTO.getDateTime().getDayOfMonth() == queryDto.getLocalDateTime().getDayOfMonth() &&
							consultationsDTO.getDateTime().getMonth() == queryDto.getLocalDateTime().getMonth()){
						notAvailablePharmacist.add(pharmacist); //Farmaceuti koje treba izbaciti iz liste dostupnih
					}
				}
			}


			Iterator itr = availablePharmacistTemp.iterator();
			while (itr.hasNext())
			{
				Pharmacist pharmacist = (Pharmacist) itr.next();
				if(notAvailablePharmacist.contains(pharmacist))
					itr.remove();
			}

		}

	return availablePharmacistTemp;


	}

	@Override
	public double calculateRating(List<Integer> ratings) {
		double ret = 0;
		for (Integer rating : ratings) {
			ret += rating;
		}
		if (ratings.size() == 0) {
			return ret;
		}
		return ret / ratings.size();
		
	}

	@Override
	public Pharmacy createPharmacy(PharmacyDTO dto) {
		Pharmacy pharmacy = new Pharmacy(dto);
		Pharmacy created = pharmacyRepo.save(pharmacy);
		PharmacyAdmin admin = (PharmacyAdmin) userService.findById(dto.getAdminId());
		admin.setPharmacy(created);
		userRepo.save(admin);
		return  created;
	}

	@Override
	public List<Pharmacy> searchPharmacies(Map<String, String> queryParams) {
		List<Pharmacy> allPharmacies = pharmacyRepo.findByDeleted(false);
		List<Pharmacy> searched = new ArrayList<Pharmacy>();
		List<Pharmacy> ret = new ArrayList<Pharmacy>();
		
		String searchParam = null;
		String filterParam = null;
		
		if (queryParams.get("search") != null) {
			searchParam = queryParams.get("search").toLowerCase();
		}
		
		if (queryParams.get("filter") != null) {
			filterParam = queryParams.get("filter").toLowerCase();
		}
		
		if (searchParam == null && filterParam == null) {
			return ret;
		}
		
		if (searchParam != null) {
			for (Pharmacy pharmacy : allPharmacies) {
				if (pharmacy.getAddress().getCity().toLowerCase().contains(searchParam) ||
						pharmacy.getAddress().getStreet().toLowerCase().contains(searchParam) ||
						pharmacy.getName().toLowerCase().contains(searchParam)) {
					searched.add(pharmacy);
				}
			}
		}
		
		if (filterParam != null) {
			if (!searched.isEmpty()) {
				for (Pharmacy pharmacy : searched) {
					double rating = this.calculateRating(pharmacy.getRatings());
					if (rating > Double.parseDouble(filterParam)) {
						ret.add(pharmacy);
					}
				}
			} else {
				for (Pharmacy pharmacy : allPharmacies) {
					double rating = this.calculateRating(pharmacy.getRatings());
					if (rating > Double.parseDouble(filterParam)) {
						ret.add(pharmacy);
					}
				}
			}
			return ret;
		} else {
			return searched;
		}
		
	}

	@Override
	public Pharmacy updatePharmacy(PharmacyDTO dto) {
		Pharmacy pharmacy = pharmacyRepo.findByIdAndDeletedIsFalse(dto.getId());
		pharmacy.setDescription(dto.getDescription());
		pharmacy.setName(dto.getName());
		pharmacy.setAddress(dto.getAddress());
		
		return pharmacyRepo.save(pharmacy);
		
	}

	@Override
	public boolean deleteDrugFromPharmacy(Long drugId, Long pharmacyId) {
		Pharmacy pharmacy = pharmacyRepo.findByIdAndDeletedIsFalse(pharmacyId);
		Drug drug = drugService.getDrug(drugId);
		pharmacy.getDrugsInStock().remove(drug);
		pharmacyRepo.save(pharmacy);
		return true;
	}

	@Override
	public boolean addDrugToPharmacy(AddDrugDTO dto, Long pharmacyId) {
		Pharmacy ph = getPharmacy(pharmacyId);
		if (ph == null) {
			return false;
		}
		
		Drug drug = drugService.getDrug(dto.getDrugId());
		if (drug == null) {
			return false;
		}
		
		if (ph.getDrugsInStock().containsKey(drug)) {
			ph.getDrugsInStock().replace(drug, ph.getDrugsInStock().get(drug) + dto.getQuantity());
		} else {
			ph.getDrugsInStock().put(drug, dto.getQuantity());
		}
		
		pharmacyRepo.save(ph);
		
		return true;
	}

	@Override
	public Pharmacist addPharmacist(EmployeeDTO dto) {
		Pharmacist pharmacist;
		WorkingHours workingHours = dto.getWorkingHours();
		PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) userService.getLoggedUser();
		Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
		workingHours.setPharmacyId(pharmacy.getId());
		if(userService.findByEmail(dto.getEmail()) != null) {
			pharmacist = (Pharmacist) userService.findByEmail(dto.getEmail());
			workingHours.setEmployeeId(pharmacist.getId());
			pharmacist.getWorkingHours().add(workingHours);
			userRepo.save(pharmacist);
			
		}
		else {
			pharmacist = new Pharmacist(dto);
			workingHours.setEmployeeId(userRepo.save(pharmacist).getId());
			pharmacist.getWorkingHours().add(workingHours);
			userRepo.save(pharmacist);
			
		}
		pharmacy.getPharmacists().add(pharmacist);
		pharmacyRepo.save(pharmacy);
		return pharmacist;
		
	}
	
	@Override
	public Dermatologist addDermatologist(EmployeeDTO dto) {
		Dermatologist dermatologist;
		WorkingHours workingHours = dto.getWorkingHours();
		PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) userService.getLoggedUser();
		Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
		workingHours.setPharmacyId(pharmacy.getId());
		if(userService.findByEmail(dto.getEmail()) != null) {
			dermatologist = (Dermatologist) userService.findByEmail(dto.getEmail());
			workingHours.setEmployeeId(dermatologist.getId());
			dermatologist.getWorkingHours().add(workingHours);
			userRepo.save(dermatologist);
			
		}
		else {
			dermatologist = new Dermatologist(dto);
			workingHours.setEmployeeId(userRepo.save(dermatologist).getId());
			dermatologist.getWorkingHours().add(workingHours);
			userRepo.save(dermatologist);
			
		}
		pharmacy.getDermatologists().add(dermatologist);
		pharmacyRepo.save(pharmacy);
		return dermatologist;
		
	}
	
	@Override
	public Pharmacist removePharmacist(Long id) {
		PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) userService.getLoggedUser();
		Pharmacist pharmacist = (Pharmacist) userService.findById(id);
		Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
		List<PharmacistConsultationDTO> consultations = appointmentService.getPharmacistConsutationsForPharmacy(pharmacy.getId());
		boolean flag = false;
		for (PharmacistConsultationDTO pharmacistConsultationDTO : consultations) {
			if(pharmacistConsultationDTO.getPharmacistId() == id && pharmacistConsultationDTO.getDateTime().isAfter(LocalDateTime.now())) {
				flag = true;
			}
		}
		
		if(!flag) {
			pharmacy.getPharmacists().remove(pharmacist);
		}
		else {
			return null;
		}
		return pharmacist;

		
	}

	
	@Override
	public Dermatologist removeDermatologist(Long id) {
		PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) userService.getLoggedUser();
		Dermatologist dermatologist = (Dermatologist) userService.findById(id);
		Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
		List<DermatologistExamDTO> exams = appointmentService.getDermatologistExamsForPharmacy(pharmacy.getId());
		boolean flag = false;
		for (DermatologistExamDTO exam : exams) {
			if(exam.getDermatologistId() == id && exam.getDateTime().isAfter(LocalDateTime.now())) {
				flag = true;
			}
		}
		
		if(!flag) {
			pharmacy.getDermatologists().remove(dermatologist);
		}
		else {
			return null;
		}
		return dermatologist;

		
	}
	


	
}
