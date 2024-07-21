package joeldockray.demos.awslambda.citydistance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SiteSearch {
	private static final ArrayList<Site> sites;
		
	public static List<SiteResult> search(Location queryLocation, int numberOfResults) throws InvalidResultNumberLimitException {
		try	{
			NegativeValueException.verifyValuePositive(numberOfResults, "The result number limit is negative.");
		}
		catch (NegativeValueException ex) {
			InvalidResultNumberLimitException.reportInvalidResultNumberLimit(numberOfResults, ex);
		}
		ArrayList<SiteResult> sitesWithDistanceAway = new ArrayList<SiteResult>();
		for (Site site : sites)	{
			sitesWithDistanceAway.add(new SiteResult(site, queryLocation));
		}	
		return SiteResult.sortedStream(sitesWithDistanceAway).limit(numberOfResults).collect(Collectors.toList());
	}
	
	static {		
		ArrayList<Site> siteList = new ArrayList<>();
		try {
			loadSites(siteList);
		}
		catch (Exception ex) {
			siteList.clear(); // Makes the failure obvious
		}
		sites = siteList;
	}
	
	public static ArrayList<Site> getSites() {
		return sites;
	}

	private static void loadSites(ArrayList<Site> siteList) throws InvalidLatitudeException, InvalidLongitudeException  {
		siteList.add(Site.createSite("Amsterdam", 52.3377672,4.8702082));
		siteList.add(Site.createSite("Ann Arbor", 42.2775,-83.74083));
		siteList.add(Site.createSite("Atlanta", 33.7915558,-84.3968513));
		siteList.add(Site.createSite("Austin", 30.4262561,-97.7444457));
		siteList.add(Site.createSite("Boston", 42.35833,-71.05972));
		siteList.add(Site.createSite("Buenos Aires", -34.61306,-58.37722));
		siteList.add(Site.createSite("Cambridge", 52.2365485,0.1402527));
		siteList.add(Site.createSite("Charlotte", 35.22694,-80.84306));
		siteList.add(Site.createSite("Chico", 39.72833,-121.83722));
		siteList.add(Site.createSite("Cincinnati", 39.12694,-84.51417));
		siteList.add(Site.createSite("Cleveland", 41.49944,-81.69528));
		siteList.add(Site.createSite("Dallas", 32.757743,-97.0376636));
		siteList.add(Site.createSite("Denver", 39.631149,-104.8973643));
		siteList.add(Site.createSite("Des Plaines", 42.03333,-87.88333));
		siteList.add(Site.createSite("Durango", 24.02028,-104.6575));
		siteList.add(Site.createSite("Florence", 43.77917,11.24611));
		siteList.add(Site.createSite("Frisco", 33.1017717,-96.8294902));
		siteList.add(Site.createSite("Gateshead", 54.934351,-1.6151623));
		siteList.add(Site.createSite("Grand Rapids", 42.96333,-85.66806));
		siteList.add(Site.createSite("Grimsby", 53.5743956,-0.1770043));
		siteList.add(Site.createSite("Houston", 29.76306,-95.36306));
		siteList.add(Site.createSite("Indore", 22.6858651,75.8701176));
		siteList.add(Site.createSite("London", 51.5121957,-0.0923356));
		siteList.add(Site.createSite("Lowell", 42.63333,-71.31611));
		siteList.add(Site.createSite("Manchester (US)", 42.99556,-71.45472));
		siteList.add(Site.createSite("Manchester (UK)", 53.4797718,-2.2551698));
		siteList.add(Site.createSite("Monterey", 36.6,-121.89444));
		siteList.add(Site.createSite("Montreal", 45.4951108,-73.7026104));
		siteList.add(Site.createSite("New York", 40.71417,-74.00583));
		siteList.add(Site.createSite("Newton Upper Falls", 42.31389,-71.21944));
		siteList.add(Site.createSite("Nottingham", 52.95361,-1.15028));
		siteList.add(Site.createSite("Orlando", 28.53833,-81.37917));
		siteList.add(Site.createSite("Plano", 33.01972,-96.69889));
		siteList.add(Site.createSite("Providence", 41.82389,-71.41278));
		siteList.add(Site.createSite("Rio de Janeiro", -22.90639,-43.18222));
		siteList.add(Site.createSite("San Francisco", 37.77472,-122.41917));
		siteList.add(Site.createSite("São Paulo", -23.5946422,-46.6881988));
		siteList.add(Site.createSite("Shanghai", 31.22222,121.45806));
		siteList.add(Site.createSite("Singapore", 1.2853181,103.8479391));
		siteList.add(Site.createSite("St Louis", 38.62722,-90.19778));
		siteList.add(Site.createSite("Stockholm", 59.3325,18.06472));
		siteList.add(Site.createSite("Sydney", -33.86778,151.20722));
		siteList.add(Site.createSite("Tampa", 27.9475,-82.45833));
		siteList.add(Site.createSite("Tokyo", 35.68944,139.69167));
		siteList.add(Site.createSite("Toledo", 39.85806,-4.0225));				
	}

}
