package joeldockray.demos.awslambda.citydistance;

import java.util.Objects;

public class Site {

	private final String name;
	private final Location location;
	
	public Site(String name, Location location) {
		this.name = name;
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
		return Objects.hash(name, location);
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}
}
