<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.expedia.hotels.HotelOffer"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Hotels Offers</title>
<style>
table tr td {
	padding: 5px 10px;
	color: #003366;
	font-weight: bold;
}

input[type=text] {
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=number] {
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.grid-container {
	display: flex;
	direction: row;
	flex-wrap: wrap;
	justify-content: center;
}

.grid-cell {
	margin: 5px;
	width: 200px;
	height: 260px;
	border-style: groove;
	background: #ffbd27;
	color: #003366
}
</style>
</head>
<body>
	<img style="width: 100%; height: 200px" alt="Welcome to expedia offers" src="expedia.jpg" />

	<form action="HotelsOffers" method="get">
		<table>
			<tr>
				<td colspan="4"><h2 style="font-style: oblique;">You can filter the offers based on the following criteria:</h2></td>
			</tr>
			<tr>
				<td>Destination Name</td>
				<td colspan="3"><input type="text" name="destinationName" value="${param.destinationName}" /></td>
			</tr>
			<tr>
				<td>Length of Stay</td>
				<td colspan="3"><input type="number" name="lengthOfStay" value="${param.lengthOfStay}" /></td>
			</tr>
			<tr>
				<td>Minimum Trip Start Date</td>
				<td><input type="text" name="minTripStartDate" value="${param.minTripStartDate}" placeholder="YYYY-MM-DD" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" /></td>
				<td>Maximum Trip Start Date</td>
				<td><input type="text" name="maxTripStartDate" value="${param.maxTripStartDate}" placeholder="YYYY-MM-DD" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" /></td>
			</tr>
			<tr>
				<td>Minimum Star Rating</td>
				<td><input type="number" name="minStarRating" value="${param.minStarRating}" /></td>
				<td>Maximum Star Rating</td>
				<td><input type="number" name="maxStarRating" value="${param.maxStarRating}" /></td>
			</tr>
			<tr>
				<td>Minimum Guest Rating</td>
				<td><input type="number" name="minGuestRating" value="${param.minGuestRating}" /></td>
				<td>Maximum Guest Rating</td>
				<td><input type="number" name="maxGuestRating" value="${param.maxGuestRating}" /></td>
			</tr>
			<tr>
				<td>Minimum Total Rate</td>
				<td><input type="number" name="minTotalRate" value="${param.minTotalRate}" /></td>
				<td>Maximum Total Rate</td>
				<td><input type="number" name="maxTotalRate" value="${param.maxTotalRate}" /></td>
			</tr>
			<tr>
				<td colspan="4"><input style="width: 50%; background-color: #003366; color: #ffbd27; border-radius: 8px; padding: 14px 40px; font-size: 15px; cursor: pointer" type="submit" value="Search Offers" /></td>
			</tr>
		</table>
	</form>

	<%
		List<HotelOffer> offers = (List<HotelOffer>) request.getAttribute("offers");
		if (offers != null) {
	%>
	<div class="grid-container">
		<%
			for (HotelOffer hotelOffer : offers) {
		%>

		<div class="grid-cell">
			<div align="center">
				<img alt="<%=hotelOffer.getHotelName()%>" src="<%=hotelOffer.getHotelImageUrl()%>" />
			</div>
			<div>
				<strong>Hotel Name:</strong>
				<%=hotelOffer.getHotelName()%>
			</div>
			<div>
				<strong>Destination:</strong>
				<%=hotelOffer.getHotelLongDestination()%>
			</div>
			<div>
				<strong>Average Price:</strong>
				<%=hotelOffer.getAveragePriceValue()%>
			</div>
			<div>
				<strong>Star Rating:</strong>
				<%=hotelOffer.getHotelStarRating()%>/5.0
			</div>
			<div>
				<strong>Length of Stay:</strong>
				<%=hotelOffer.getLengthOfStay()%>
			</div>
			<div align="center">
				<a style="color: #003366" href="#" onclick="window.open(decodeURIComponent('<%=hotelOffer.getHotelInfositeUrl()%>'))">Offer More Details</a>
			</div>
		</div>

		<%
			}
		%>
	</div>
	<%
		}
	%>
	<%
		if (offers != null && offers.isEmpty()) {
	%>
	<h3 style="text-align: center; color: #003366">No data found</h3>
	<%
		}
	%>
</body>
</html>