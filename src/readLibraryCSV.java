
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class readLibraryCSV {
	
	public static void main (String [] args){
		ArrayList<String[]> circulation = new ArrayList<String[]>();
		ArrayList<String[]> computerSessions = new ArrayList<String[]>();
		ArrayList<String[]> visitors = new ArrayList<String[]>();
		ArrayList<String[]> wifiUse = new ArrayList<String[]>();
		readLibraryCSV library = new readLibraryCSV();
		
		library.run("Libraries_-_2014_Circulation_by_Location.csv", circulation);
		library.run("Libraries_-_2014_Computer_Sessions_by_Location.csv", computerSessions);
		library.run("Libraries_-_2014_Visitors_by_Location.csv", visitors);
		library.run("Libraries_-_WiFi_Usage__2011-2014_.csv", wifiUse);

		int c = library.getAllCirculation(circulation);
		int s = library.getAllSessions(computerSessions);
		int v = library.getAllVisitors(visitors);
		int w = library.getWifi2014(wifiUse);
		
		library.getPopularService(library.getVisitorPercentage(v, w, c, s));

		}
	
	//reads the csv files and stores it in an array list
	//got pointers from http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	//note decide to not parse info even farther, ex, there are many numbers that could have been made to int, but they were mixed with strings
	//so I decided that I would take string and parse them into ints if need be
	public void run(String resource, ArrayList<String[]> csv) {
		InputStream source = getClass().getClassLoader().getResourceAsStream(resource);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new InputStreamReader(source));
			while ((line = br.readLine()) != null) {
			String [] location = line.split(cvsSplitBy);
			csv.add(location);
			}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
	//gets the total number of computer sessions yearly to date at all libraries in Chicago
	public Integer getAllSessions(ArrayList<String[]> computerSessions){
		int sessions = 0;
		//need to retrieve each YTD of each location then convert it to int, then add it;
		//i = 1, because first item in arraylist is the category names ex Location, City, zip etc.  
		for(int i = 1; i<computerSessions.size();i++){
			String temp [] = computerSessions.get(i);
			int ytd = Integer.parseInt(temp[16]);
			sessions+= ytd ;
		}
		System.out.println("The total number of computer sessions during 2014 for all libraries in Chicago are "+sessions);
		return sessions;
	}
	
	//gets the total number of circulations (book rentals, renewals, etc) YTD at all libraries in Chicago
	//same structure as getAllSessions (happened to be that CSV file is structured the same way)
	public Integer getAllCirculation(ArrayList<String[]> circulations){
		int books = 0;
		for(int i = 1; i<circulations.size();i++){
			String temp [] = circulations.get(i);
			int ytd = Integer.parseInt(temp[16]);
			books+= ytd ;
		}
		System.out.println("The total number of circulations (book rentals, renewals, etc) during 2014 for all libraries in Chicago are "+books);
		return books;
	}
	
	//gets the total number of visitors YTD at all libraries in Chicago
	//same structure as getAllSessions (happened to be that CSV file is structured the same way)
	public Integer getAllVisitors(ArrayList<String[]> visits){
		int visitors = 0;
		for(int i = 1; i<visits.size();i++){
			String temp [] = visits.get(i);
			int ytd = Integer.parseInt(temp[16]);
			visitors+= ytd ;
		}
		System.out.println("The total number of visitors during 2014 for all libraries in Chicago are "+visitors);
		return visitors;
	}
	
	//the CSV file for wifi sessions includes years 2011-2014, I'm studying a specific year 2014
	//the following method finds the total number of wifi sessions for 2014
	public Integer getWifi2014(ArrayList<String[]> wifiSess){
		int wifi = 0;
		//checks for the year 2014, and then adds number of sessions all together
		for(int i = 1; i<wifiSess.size();i++){
			String temp [] = wifiSess.get(i);
			if(temp[1].equals("2014")){
			int mtd = Integer.parseInt(temp[2]);
			wifi+= mtd ;
			}
		}
		System.out.println("The total number of wifi sessions during 2014 for all libraries in Chicago are "+wifi);
		return wifi;
	}
	
	//methods compares total visitors to total computer sessions, wifi usage, and computer sessions, 
	// which will return a percentage rate respective to each comparison 
	public int[] getVisitorPercentage(int visitors, int wifi, int circulation, int sessions){
		 int rateOfWifi= (int)(((double)wifi/(double)visitors) * 100);
		 int rateOfCirculation= (int)(((double)circulation/(double)visitors) * 100);
		 int rateOfSessions= (int)(((double)sessions/(double)visitors) * 100);
		 int [] ratesOfservice = {rateOfWifi, rateOfCirculation,rateOfSessions};
		 
		 //The following print statements inform user of the different percentages, as well as possibly explain some outcomes.  
		 System.out.println("The percentage of visitors at Chicago libraries who use wifi are " +rateOfWifi+ "%");
		 if(rateOfWifi>100){
				System.out.println("Percentage over 100 implies that visitors either have more than one device that uses wifi or non visitors use the wifi at libraries");
			 }
		 
		 System.out.println("The percentage of visitors at Chicago libraries who have rented, borrowed, or some similar type of book circulation  are " +rateOfCirculation+ "%");
		 //would have liked to be more specific but couldn't find a data set specific enough that breaks down each category of circulations
		 if(rateOfCirculation>100){
				System.out.println("Percentage over 100 implies that visitors borrow more than one book, or for every visitors there is at least one circulation to their name");
			 }
		 
		 System.out.println("The percentage of visitors at Chicago libraries who use computers are " +rateOfSessions+ "%");
		 if(rateOfSessions>100){
				System.out.println("Percentage over 100 implies that visitors have multiple computer sessions in the same trip");
			 }
		 
		 return ratesOfservice;
	}
	
	//compare above outcomes to eachother and then decide what service is most used
	public String getPopularService(int[] percentages){
		String service = "";
		if(percentages[0]>percentages[1]&& percentages[0]>percentages[2]){
			service = "wifi";
			System.out.println("The percentage of wifi sessions to visitors, "+percentages[0]+"%, is greater than the percentage of computer sessions to visitors and circulations to visitors");
		}else if(percentages[1]>percentages[0]&& percentages[1]>percentages[2]){
			service = "circulations";
			System.out.println("The percentage of circulations to visitors, "+percentages[1]+"%, is greater than the percentage of computer sessions to visitors and wifi sessions to visitors");
		}else if(percentages[2]>percentages[1]&& percentages[2]>percentages[1]){
			service = "computers";
			System.out.println("The percentage of computer sessions to visitors, "+percentages[2]+"%, is greater than the percentage of wifi sessions to visitors and circulations to visitors");
		}
		return service;
		
	}
}