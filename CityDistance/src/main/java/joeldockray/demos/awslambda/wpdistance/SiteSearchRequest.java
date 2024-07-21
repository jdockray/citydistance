package joeldockray.demos.awslambda.citydistance;

public class SiteSearchRequest {
	private String latitude;
	private String longitude;
	private String numberOfResults;
	private static final int DEFAULT_NUMBER_OF_RESULTS = 10;

	public SiteSearchRequest(String latitude, String longitude, String numberOfResults) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.numberOfResults = numberOfResults;
	}

	public SiteSearchRequest() {
		this("0", "0", "0");
	}	
	
	public Location parseLocation() throws InvalidLatitudeException, InvalidLongitudeException {
		double latitudeNumber = 0;
		try {
			latitudeNumber = Double.parseDouble(latitude);
		}
		catch (NumberFormatException ex) {
			InvalidLatitudeException.reportInvalidLatitude(latitude, ex);
		}
		double longitudeNumber = 0;
		try {
			longitudeNumber = Double.parseDouble(longitude);
		}
		catch (NumberFormatException ex) {
			InvalidLongitudeException.reportInvalidLongitude(longitude, ex);
		}
		return Location.getLocation(latitudeNumber, longitudeNumber);
	}	
	
	public int parseNumberOfResults() throws InvalidResultNumberLimitException {
		if (numberOfResults == "") {
			return DEFAULT_NUMBER_OF_RESULTS;
		}
		try {
			int value = Integer.parseInt(numberOfResults);
			NegativeValueException.verifyValuePositive(value);
			return value;
		}
		catch (NumberFormatException | NegativeValueException ex) {
			// Always throws
			InvalidResultNumberLimitException.reportInvalidResultNumberLimit(numberOfResults, ex);
		}
		throw new AssertionError(); // Assert unreachable code
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}	
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}	
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setNumberOfResults(String numberOfResults) {
		this.numberOfResults = numberOfResults;
	}	
	
	public String getNumberOfResults() {
		return numberOfResults;
	}	
}
