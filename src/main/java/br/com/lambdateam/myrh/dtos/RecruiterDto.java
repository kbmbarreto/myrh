package br.com.lambdateam.myrh.dtos;

import java.sql.Date;
import javax.validation.constraints.NotNull;

public class RecruiterDto {
	
	private Long id;
	@NotNull(message = "Data is required")
	private Date contactDate;
	@NotNull(message = "Name is required")
	private String name;
	private String telephone;
	private String email;
	private String company;
	@NotNull(message = "Contact type is required")
	private String contactType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
}