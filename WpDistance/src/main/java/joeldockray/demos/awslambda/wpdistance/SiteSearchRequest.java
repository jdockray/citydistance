package joeldockray.demos.awslambda.wpdistance;

public class SiteSearchRequest {
	private String latitude;
	private String longitude;
	private String numberOfResults;

	public SiteSearchRequest(String latitude, String longitude, String numberOfResults)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.numberOfResults = numberOfResults;
	}

	public SiteSearchRequest()
	{
		this("0", "0", "0");
	}	
	
	public Location parseLocation() throws InvalidLocationException {
		try {
			return Location.getLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
		}
		catch (NumberFormatException ex) {
			throw new InvalidLocationException(ex);
		}		
	}	
	
	public int parseNumberOfResults() throws InvalidResultNumberLimitException {
		try {
			int value = Integer.parseInt(numberOfResults);
			NegativeValueException.verifyValuePositive(value);
			return value;
		}
		catch (NumberFormatException | NegativeValueException ex) {
			throw new InvalidResultNumberLimitException(ex);
		}
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
