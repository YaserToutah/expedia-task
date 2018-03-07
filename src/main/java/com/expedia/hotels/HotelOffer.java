package com.expedia.hotels;

public class HotelOffer {

	private String hotelName;
	private String hotelStarRating;
	private String hotelImageUrl;
	private double averagePriceValue;
	private String hotelInfositeUrl;
	private String hotelLongDestination;
	private long lengthOfStay;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelStarRating() {
		return hotelStarRating;
	}

	public void setHotelStarRating(String hotelStarRating) {
		this.hotelStarRating = hotelStarRating;
	}

	public String getHotelImageUrl() {
		return hotelImageUrl;
	}

	public void setHotelImageUrl(String hotelImageUrl) {
		this.hotelImageUrl = hotelImageUrl;
	}

	public double getAveragePriceValue() {
		return averagePriceValue;
	}

	public void setAveragePriceValue(double averagePriceValue) {
		this.averagePriceValue = averagePriceValue;
	}


	public String getHotelInfositeUrl() {
		return hotelInfositeUrl;
	}

	public void setHotelInfositeUrl(String hotelInfositeUrl) {
		this.hotelInfositeUrl = hotelInfositeUrl;
	}

	public String getHotelLongDestination() {
		return hotelLongDestination;
	}

	public void setHotelLongDestination(String hotelLongDestination) {
		this.hotelLongDestination = hotelLongDestination;
	}
	

	public long getLengthOfStay() {
		return lengthOfStay;
	}

	public void setLengthOfStay(long lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}

	@Override
	public String toString() {
		return "HotelOffer [hotelName=" + hotelName + ", hotelStarRating=" + hotelStarRating + ", hotelImageUrl="
				+ hotelImageUrl + ", averagePriceValue=" + averagePriceValue + ", hotelInfositeUrl=" + hotelInfositeUrl
				+ ", hotelLongDestination=" + hotelLongDestination + ", lengthOfStay=" + lengthOfStay + "]" + System.lineSeparator();
	}

	
	
}
