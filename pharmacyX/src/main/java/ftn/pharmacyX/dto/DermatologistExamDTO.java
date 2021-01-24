package ftn.pharmacyX.dto;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.users.Dermatologist;

import java.time.LocalDateTime;
import java.util.List;

public class DermatologistExamDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Long pharmacyId;
    private String pharmacyName;
    private Long patientId;
    private String therapyDescription;
    private List<Drug> therapyDrugs;
    private Long dermatologistId;
    private String dermatologistName;
    private String diagnosis;
    private double price;
    private boolean deleted = false;

    public DermatologistExamDTO() {
    }

    public DermatologistExamDTO(Long id, LocalDateTime dateTime, Long pharmacyId, Long patientId, String therapyDescription, List<Drug> therapyDrugs, Long dermatologistId, String diagnosis, double price, boolean deleted) {
        this.id = id;
        this.dateTime = dateTime;
        this.pharmacyId = pharmacyId;
        this.patientId = patientId;
        this.therapyDescription = therapyDescription;
        this.therapyDrugs = therapyDrugs;
        this.dermatologistId = dermatologistId;
        this.diagnosis = diagnosis;
        this.price = price;
        this.deleted = deleted;
    }

    public DermatologistExamDTO(DermatologistExam exam) {
        this.id = exam.getId();
        this.dateTime = exam.getDateTime();
        this.pharmacyId = exam.getPharmacy().getId();
        if(exam.getPatient() == null){
            this.patientId = null;
        }
        else{
            this.patientId = exam.getPatient().getId();
        }
        this.therapyDescription = exam.getTherapyDescription();
        this.therapyDrugs = exam.getTherapyDrugs();
        this.dermatologistId = exam.getDermatologist().getId();
        this.diagnosis = exam.getDiagnosis();
        this.price = exam.getPrice();
        this.deleted = exam.isDeleted();
        this.dermatologistName = exam.getDermatologist().getFirstName() + " " + exam.getDermatologist().getLastName();
        this.pharmacyName = exam.getPharmacy().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getTherapyDescription() {
        return therapyDescription;
    }

    public void setTherapyDescription(String therapyDescription) {
        this.therapyDescription = therapyDescription;
    }

    public List<Drug> getTherapyDrugs() {
        return therapyDrugs;
    }

    public void setTherapyDrugs(List<Drug> therapyDrugs) {
        this.therapyDrugs = therapyDrugs;
    }

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getDermatologistName() {
        return dermatologistName;
    }

    public void setDermatologistName(String dermatologistName) {
        this.dermatologistName = dermatologistName;
    }
}