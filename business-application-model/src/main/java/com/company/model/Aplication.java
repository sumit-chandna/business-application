package com.company.model;

public class Aplication {

	private Integer amortization;
	private Applicant applicant;
	private Integer downPayment;
	private ValidationErrorDO errors;
	private Integer morgageAmount;
	private Property property;

	public Integer getAmortization() {
		return amortization;
	}

	public void setAmortization(Integer amortization) {
		this.amortization = amortization;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Integer getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(Integer downPayment) {
		this.downPayment = downPayment;
	}

	public ValidationErrorDO getErrors() {
		return errors;
	}

	public void setErrors(ValidationErrorDO errors) {
		this.errors = errors;
	}

	public Integer getMorgageAmount() {
		return morgageAmount;
	}

	public void setMorgageAmount(Integer morgageAmount) {
		this.morgageAmount = morgageAmount;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
}
