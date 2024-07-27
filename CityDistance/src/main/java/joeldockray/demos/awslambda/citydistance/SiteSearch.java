package joeldockray.demos.awslambda.citydistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SiteSearch {
	
	static final String RESOURCES_FILE = "/Cities.csv";
	
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

	private static void loadSites(ArrayList<Site> siteList) throws InvalidLatitudeException, InvalidLongitudeException, IOException {
		siteList.clear();
		BufferedReader sites = new BufferedReader(new InputStreamReader(SiteSearch.class.getResourceAsStream(RESOURCES_FILE)));
		String line = sites.readLine();
		while (line != null) {
			String[] columns = line.split(",", 3);
			if (columns.length < 3) {
				throw new IOException();
			}
			siteList.add(Site.createSite(columns[2].replace("\"", ""), Double.valueOf(columns[0]), Double.valueOf(columns[1])));
			line = sites.readLine();
		}
	}
}
