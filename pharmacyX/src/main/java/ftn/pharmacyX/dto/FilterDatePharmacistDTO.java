package ftn.pharmacyX.dto;

import java.time.LocalDateTime;

public class FilterDatePharmacistDTO {
    private String dateTime;
    private Long pharmacyId;
    private LocalDateTime localDateTime;

    public FilterDatePharmacistDTO() {
    }

    public FilterDatePharmacistDTO(String dateTime, Long pharmacyId, LocalDateTime localDateTime) {
        this.dateTime = dateTime;
        this.pharmacyId = pharmacyId;
        this.localDateTime = localDateTime;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
