package joeldockray.demos.awslambda.wpdistance;

import java.util.Objects;

public class Site {

	public final String siteName;   // Immutable
	public final Location location; // Immutable
	
	public Site(String siteName, Location location) {
		this.siteName = siteName;
		this.location = location;
	}
		
	public static Site createSite(String siteName, double latitude, double longitude)
		throws InvalidLatitudeException, InvalidLongitudeException {
		return new Site(siteName, Location.getLocation(latitude, longitude));
	}
	
	public Site() {
		this("", new Location());
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Site && obj.hashCode() == hashCode();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(siteName, location);
	}
}
