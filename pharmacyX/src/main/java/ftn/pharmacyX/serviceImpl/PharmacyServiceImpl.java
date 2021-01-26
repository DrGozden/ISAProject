package ftn.pharmacyX.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.PharmacistConsultationDTO;
import ftn.pharmacyX.model.WorkingHours;
import ftn.pharmacyX.model.users.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.repository.PharmacyRepository;
import ftn.pharmacyX.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepo;

	@Autowired
	private AppointmentServiceImpl appointmentService;
	
	
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
	public List<Pharmacist> getAvailablePharmacist(PharmacistConsultationDTO queryDto) {
		List<Pharmacist> availablePharmacistTemp = new ArrayList<>();
		Pharmacy pharmacy = getPharmacy(queryDto.getPharmacyId());

		for(Pharmacist pharmacist: pharmacy.getPharmacists()){
			for(WorkingHours wh: pharmacist.getWorkingHours()){
				if(wh.getDay().name().equalsIgnoreCase(queryDto.getDateTime().getDayOfWeek().name())){
					if(queryDto.getDateTime().getHour() >= (wh.getStartTime().getHour()) && queryDto.getDateTime().getHour() <= (wh.getEndTime().getHour()) ){
						availablePharmacistTemp.add(pharmacist); //Rade u periodu koji je zahtevan
					}
				}
			}
		}


		List<Pharmacist> notAvailablePharmacist = new ArrayList<>();

		List<PharmacistConsultationDTO> consultations = appointmentService.getPharmacistConsutationsForPharmacy(pharmacy.getId());
		for (PharmacistConsultationDTO consultationsDTO: consultations) {
			for(Pharmacist pharmacist: availablePharmacistTemp){
				System.out.println(queryDto.getDateTime().getMonth() + "ovo je mesec");
				System.out.println(queryDto.getDateTime().getDayOfMonth() + "ovo je dan");
				if(consultationsDTO.getDateTime().getHour() == queryDto.getDateTime().getHour() && consultationsDTO.getDateTime().getDayOfMonth() == queryDto.getDateTime().getDayOfMonth() &&
						consultationsDTO.getDateTime().getMonth() == queryDto.getDateTime().getMonth()){
					System.out.println("Vreme i datum su isti");
				}
				if(consultationsDTO.getPharmacistId().equals(pharmacist.getId()) && consultationsDTO.getDateTime().getHour() == queryDto.getDateTime().getHour()
				&& consultationsDTO.getDateTime().getDayOfMonth() == queryDto.getDateTime().getDayOfMonth() &&
						consultationsDTO.getDateTime().getMonth() == queryDto.getDateTime().getMonth()){
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

	return availablePharmacistTemp;


	}

	public double calculateRating(List<Integer> ratings) {
		int ret = 0;
		for (Integer rating : ratings) {
			ret += rating;
		}
		if (ratings.size() == 0) {
			return ret;
		}
		return ret / ratings.size();
		
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
	
}
