package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class SiteResultOutput {

	private static final int COORD_DECIMAL_PLACES = 5;
	private static final String ORDINAL_FORMAT = "%." + COORD_DECIMAL_PLACES + "f";
	private String siteName;
	private String latitude;
	private String longitude;
	private String kmDistanceAway;
	
	public SiteResultOutput(SiteResult result) {
		siteName = result.site.siteName;
		Location roundedLocation = result.site.location.round(COORD_DECIMAL_PLACES);
		latitude = String.format(ORDINAL_FORMAT, roundedLocation.latitude);
		longitude = String.format(ORDINAL_FORMAT, roundedLocation.longitude);
		// In future the distance away could be rounded to a number of significant figures.
		kmDistanceAway = String.valueOf(Math.round(result.kmDistanceAway));
	}
	
	public SiteResultOutput() {
		this(new SiteResult());
	}

	public static ArrayList<SiteResultOutput> parseResults(Stream<SiteResult> stream) {
		ArrayList<SiteResultOutput> stringResults = new ArrayList<SiteResultOutput>();
		stream.forEachOrdered((result) -> stringResults.add(new SiteResultOutput(result)));
		return stringResults;
	}
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}	
	
	public String getSiteName() {
		return siteName;
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
	
	public void setKmDistanceAway(String kmDistanceAway) {
		this.kmDistanceAway = kmDistanceAway;
	}	
	
	public String getKmDistanceAway() {
		return kmDistanceAway;
	}
		
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof SiteResultOutput && obj.hashCode() == hashCode();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(siteName, latitude, longitude, kmDistanceAway);
	}
}
