package hu.webuni.transport.nyhp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDto {
	
	private long id;
	@NotNull
	@NotEmpty
	private String isoCode;
	@NotNull
	@NotEmpty
	private String zipcode;
	@NotNull
	@NotEmpty
	private String city;
	@NotNull
	@NotEmpty
	private String street;
	private String streetNumber;
	private double latitude;
	private double longitude;
	
	public AddressDto() {}

	public AddressDto(long id, String isoCode, String zipcode, String city, String street, String streetNumber,
			double latitude, double longitude) {
		super();
		this.id = id;
		this.isoCode = isoCode;
		this.zipcode = zipcode;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
