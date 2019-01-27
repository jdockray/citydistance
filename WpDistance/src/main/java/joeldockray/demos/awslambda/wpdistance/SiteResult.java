package joeldockray.demos.awslambda.wpdistance;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class SiteResult {

	public final Site site;	// Immutable
	public final double kmDistanceAway;
	
	private static final Comparator<SiteResult> CLOSEST_SITES_FIRST = new Comparator<SiteResult>()
	{
		@Override
		public int compare(SiteResult siteA, SiteResult siteB) {			
			return Double.compare(siteA.kmDistanceAway, siteB.kmDistanceAway);
		}	
	};	
	
	private SiteResult(Site site, double kmDistanceAway)
	{
		this.site = site;
		this.kmDistanceAway = kmDistanceAway;
	}
	
	public SiteResult(Site site, Location queryLocation)
	{
		this(site, Location.getDistance(site.location, queryLocation));
	}
	
	public SiteResult() {
		this(new Site(), 0);
	}

	public static SiteResult createSiteResult(Site site, double kmDistanceAway) throws NegativeValueException
	{
		NegativeValueException.verifyValuePositive(kmDistanceAway, "The distance away is negative.");
		return new SiteResult(site, kmDistanceAway);
	}
	
	public static Stream<SiteResult> sortedStream(Collection<SiteResult> results) {
		return results.stream().sorted(SiteResult.CLOSEST_SITES_FIRST);
	}
	
	@Override
	public boolean equals(Object obj) {
	    return obj != null && obj instanceof SiteResult && obj.hashCode() == hashCode();
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(site, kmDistanceAway);
	}
}

