package ftn.pharmacyX.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Drug implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String code;
	@OneToOne
	private DrugSpecification specification;
	@Column
	private boolean deleted = false;
	
	public Drug() {
		
	}

	public Drug(Long id, String name, String code, DrugSpecification specification, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.specification = specification;
		this.deleted = deleted;
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
