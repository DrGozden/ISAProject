package ftn.pharmacyX.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ftn.pharmacyX.enums.DayOfWeek;

@Entity
public class WorkingHours implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Long pharmacyId;
	@Column
	private Long employeeId;
	@Enumerated
	private DayOfWeek day;
	@Column
	private LocalTime startTime;
	@Column
	private LocalTime endTime;
	
	public WorkingHours() {
		
	}

	public WorkingHours(Long id, Long pharmacyId, Long employeeId, DayOfWeek day, LocalTime startTime,
			LocalTime endTime) {
		super();
		this.id = id;
		this.pharmacyId = pharmacyId;
		this.employeeId = employeeId;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
}
