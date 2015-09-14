import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class readLibraryCSVTest {
	readLibraryCSV test = new readLibraryCSV();
	ArrayList<String[]> circulation = new ArrayList<String[]>();
	ArrayList<String[]> computerSessions = new ArrayList<String[]>();
	ArrayList<String[]> visitors = new ArrayList<String[]>();
	ArrayList<String[]> wifiUse = new ArrayList<String[]>();

	// Method is called before each test case execution  
	@Before
	public void setUp() throws Exception {

	}

	// Method is called after each test case execution
	@After
	public void tearDown() throws Exception {

	}
	
	//Checks that getAllSession will return a non null value
	@Test
	public void testGetAllSessions() {
		test.run("Libraries_-_2014_Computer_Sessions_by_Location.csv",computerSessions);
		Assert.assertNotNull(test.getAllSessions(computerSessions));
	}
	
	//Checks that getAllCirculation will return a non null value
	@Test
	public void testGetAllCirculation() {
		test.run("Libraries_-_2014_Circulation_by_Location.csv",circulation);
		Assert.assertNotNull(test.getAllCirculation(circulation));
	}
	
	//Checks that getAllVisitors will return a non null value
	@Test
	public void testGetAllVisitors() {
		test.run("Libraries_-_2014_Visitors_by_Location.csv",visitors);
		Assert.assertNotNull(test.getAllVisitors(visitors));
	}
	
	//Checks that getWifi2014 will return a non null value
	@Test
	public void testGetWifi2014() {
		test.run("Libraries_-_WiFi_Usage__2011-2014_.csv",wifiUse);
		Assert.assertNotNull(test.getWifi2014(wifiUse));
	}
	
	//Checks that getvisitorPercentage will not be null
	//checks that GetVisitorPercentage will return something more than null
	@Test
	public void testGetVisitorPercentage() {
		int visitor =80000;
		int wifi = 23240;
		int circulation = 40459;
		int computers= 36587;
		int [] percentages = null;
		Assert.assertNotNull(test.getVisitorPercentage(visitor, wifi, circulation, computers));
		Assert.assertFalse(test.getVisitorPercentage(visitor, wifi, circulation, computers).equals(percentages));
		
	}

	//Checks that getPopularservice will not be null
	//checks that with given percentages, computers is the most popular service
	@Test
	public void testGetPopularService() {
		int [] temp = {21, 38, 102};
		Assert.assertNotNull(test.getPopularService(temp));
		Assert.assertTrue(test.getPopularService(temp).equals("computers"));
	}

}
