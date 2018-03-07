package com.expedia.hotels;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HotelOfferService {

	static final String URL = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";

	/**
	 * This methods returns list that contains HotelOffer objects depends on the
	 * passed filters.
	 * 
	 * 
	 * @param filters
	 * @return
	 * @throws Exception
	 */
	public List<HotelOffer> getHotelsOffers(Map<String, String> filters) throws Exception {

		HotelOfferService hotelOfferService = new HotelOfferService();
		URI readyUrl = hotelOfferService.buildUrl(URL, filters);
		String responseAsText = hotelOfferService.doRequest(readyUrl);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(responseAsText);
		return hotelOfferService.getListOfHotesOffersFromJSONObjectAsJavaObj(jsonObject);
	}

	public URI buildUrl(String baseUrl, Map<String, String> filtersParameters) throws URISyntaxException {

		URIBuilder uriBuilder = new URIBuilder(baseUrl);
		for (Entry<String, String> entry : filtersParameters.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		return uriBuilder.build();
	}

	/**
	 * This method hits on the passed targetURL and return it's response as
	 * String.
	 * 
	 * @param targetURL
	 * @return the response as String
	 * @throws Exception
	 */
	private String doRequest(URI targetURL) throws Exception {

		HttpURLConnection connection = null;
		BufferedReader br = null;
		try {
			connection = (HttpURLConnection) targetURL.toURL().openConnection();
			connection.setDoOutput(true);

			InputStream is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			throw new Exception("Faile to send request due to" + e.getMessage());
		} finally {
			if (br != null) {
				br.close();
			}
		}

	}

	/**
	 * This method parse the passed jsonObject into java objects and returns the
	 * java objects as list. It does that by iterating over Hotel array, gets
	 * it's value and set each value to the corresponding variable in the
	 * HotelOffer bean.
	 * 
	 * @param jsonObject
	 * @return list of HotelOffer java objects
	 */
	public List<HotelOffer> getListOfHotesOffersFromJSONObjectAsJavaObj(JSONObject jsonObject) {

		JSONObject offersObj = (JSONObject) jsonObject.get("offers");
		JSONArray hotelArray = (JSONArray) offersObj.get("Hotel");
		List<HotelOffer> hotelOfferJavaList = new ArrayList<HotelOffer>();
		if (hotelArray == null) {
			return hotelOfferJavaList;
		}
		for (Object hotelOfferObj : hotelArray) {

			JSONObject hotelOfferJson = (JSONObject) hotelOfferObj;
			HotelOffer hotelOffer = new HotelOffer();

			JSONObject jsonInnerHotelInfoObj = (JSONObject) hotelOfferJson.get("hotelInfo");
			hotelOffer.setHotelName((String) jsonInnerHotelInfoObj.get("hotelName"));
			JSONObject jsonInnerHotelPricingInfo = (JSONObject) hotelOfferJson.get("hotelPricingInfo");
			hotelOffer.setAveragePriceValue((Double) jsonInnerHotelPricingInfo.get("averagePriceValue"));
			hotelOffer.setHotelImageUrl((String) jsonInnerHotelInfoObj.get("hotelImageUrl"));
			JSONObject jsonInnerHotelUrls = (JSONObject) hotelOfferJson.get("hotelUrls");
			hotelOffer.setHotelInfositeUrl((String) jsonInnerHotelUrls.get("hotelInfositeUrl"));
			hotelOffer.setHotelLongDestination((String) jsonInnerHotelInfoObj.get("hotelLongDestination"));
			hotelOffer.setHotelStarRating((String) jsonInnerHotelInfoObj.get("hotelStarRating"));
			JSONObject jsonInnerDateRangeObj = (JSONObject) hotelOfferJson.get("offerDateRange");
			hotelOffer.setLengthOfStay((Long) jsonInnerDateRangeObj.get("lengthOfStay"));

			hotelOfferJavaList.add(hotelOffer);
		}
		return hotelOfferJavaList;
	}
}
