package com.expedia.hotels;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HotelsOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelsOfferServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> filters = new HashMap<>();
		String destinationName = request.getParameter("destinationName");
		String minTripStartDate = request.getParameter("minTripStartDate");
		String maxTripStartDate = request.getParameter("maxTripStartDate");
		String lengthOfStay = request.getParameter("lengthOfStay");
		String minStarRating = request.getParameter("minStarRating");
		String maxStarRating = request.getParameter("maxStarRating");
		String minTotalRate = request.getParameter("minTotalRate");
		String maxTotalRate = request.getParameter("maxTotalRate");
		String minGuestRating = request.getParameter("minGuestRating");
		String maxGuestRating = request.getParameter("maxGuestRating");

		if (destinationName != null && !destinationName.isEmpty()) {
			filters.put("destinationName", destinationName);
		}
		if (minTripStartDate != null && !minTripStartDate.isEmpty()) {
			filters.put("minTripStartDate", minTripStartDate);
		}
		if (maxTripStartDate != null && !maxTripStartDate.isEmpty()) {
			filters.put("maxTripStartDate", maxTripStartDate);
		}
		if (lengthOfStay != null && !lengthOfStay.isEmpty()) {
			filters.put("lengthOfStay", lengthOfStay);
		}
		if (minStarRating != null && !minStarRating.isEmpty()) {
			filters.put("minStarRating", minStarRating);
		}
		if (maxStarRating != null && !maxStarRating.isEmpty()) {
			filters.put("maxStarRating", maxStarRating);
		}
		if (minTotalRate != null && !minTotalRate.isEmpty()) {
			filters.put("minTotalRate", minTotalRate);
		}
		if (maxTotalRate != null && !maxTotalRate.isEmpty()) {
			filters.put("maxTotalRate", maxTotalRate);
		}
		if (minGuestRating != null && !minGuestRating.isEmpty()) {
			filters.put("minGuestRating", minGuestRating);
		}
		if (maxGuestRating != null && !maxGuestRating.isEmpty()) {
			filters.put("maxGuestRating", maxGuestRating);
		}

		HotelOfferService hotelOfferService = new HotelOfferService();
		List<HotelOffer> offers;
		try {
			offers = hotelOfferService.getHotelsOffers(filters);
			request.setAttribute("offers", offers);
			RequestDispatcher dispatcher = request.getRequestDispatcher("hotels.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
