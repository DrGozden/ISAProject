package ftn.pharmacyX.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Long userId;
	@Column
	private LocalDate startDate;
	@Column
	private LocalDate endDate;
	@Column
	private boolean accepted = false;
	@Column
	private String rejectDescription;
	@Column
	private boolean deleted = false;

	public Vacation() {

	}

	public Vacation(Long id, Long userId, LocalDate startDate, LocalDate endDate, boolean accepted,
			String rejectDescription, boolean deleted) {
		super();
		this.id = id;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accepted = accepted;
		this.rejectDescription = rejectDescription;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getRejectDescription() {
		return rejectDescription;
	}

	public void setRejectDescription(String rejectDescription) {
		this.rejectDescription = rejectDescription;
	}

}
