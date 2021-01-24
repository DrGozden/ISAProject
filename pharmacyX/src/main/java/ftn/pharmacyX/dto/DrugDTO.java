package ftn.pharmacyX.dto;

import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.DrugSpecification;

import javax.persistence.*;

public class DrugDTO {
    private Long id;
    private String name;
    private String code;
    private DrugSpecification specification;
    private boolean deleted = false;

    public DrugDTO() {
    }

    public DrugDTO(Long id, String name, String code, DrugSpecification specification, boolean deleted) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.specification = specification;
        this.deleted = deleted;
    }

    public DrugDTO(Drug drug) {
        this.id = drug.getId();
        this.name = drug.getName();
        this.code = drug.getCode();
        this.specification = drug.getSpecification();
        this.deleted = drug.isDeleted();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DrugSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(DrugSpecification specification) {
        this.specification = specification;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
