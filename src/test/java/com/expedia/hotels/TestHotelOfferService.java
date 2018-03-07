package com.expedia.hotels;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestHotelOfferService extends HotelOfferService{

	@Test
	public void givenJSONObject_WhenParse_ReturnNumberOfHotesOffers() {

		HotelOfferService hotelService = new HotelOfferService();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(TestConstants.hotelResponseWithThreeOffers);
			assertEquals(3, hotelService.getListOfHotesOffersFromJSONObjectAsJavaObj(jsonObject).size());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenJSONObject_WhenParse_ThenReturnHotelName_The_Peninsula_New_York() {

		HotelOfferService hotelService = new HotelOfferService();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(TestConstants.hotelResponseWithHotelName_The_Peninsula_New_York);
			assertEquals("The Peninsula New York",
					hotelService.getListOfHotesOffersFromJSONObjectAsJavaObj(jsonObject).get(0).getHotelName());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuildUrlWithoutFilters() throws URISyntaxException {

		HotelOfferService hotelService = new HotelOfferService();
		Map<String, String> filters = new HashMap<>();
		assertEquals(HotelOfferService.URL, hotelService.buildUrl(HotelOfferService.URL, filters).toString());
	}

	@Test
	public void testBuildUrlWithFilters() throws URISyntaxException {

		HotelOfferService hotelService = new HotelOfferService();
		StringBuilder sb = new StringBuilder(HotelOfferService.URL);
		sb.append("&destinationName=America&minTripStartDate=2018-03-12");
		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("destinationName", "America");
		filters.put("minTripStartDate", "2018-03-12");
		assertEquals(sb.toString(), hotelService.buildUrl(HotelOfferService.URL, filters).toString());
	}
}
