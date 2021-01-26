package ftn.pharmacyX.dto;

import java.time.LocalDateTime;

public class NewConsultationDTO {
    private String dateTime;
    private Long pharmacyId;
    private Long pharmacistId;

    public NewConsultationDTO() {
    }

    public NewConsultationDTO(String dateTime, Long pharmacyId, Long pharmacistId) {
        this.dateTime = dateTime;
        this.pharmacyId = pharmacyId;
        this.pharmacistId = pharmacistId;
    }


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }
}
