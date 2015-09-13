
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class readLibraryCSV {
	public static void main (String [] args){
		ArrayList<String[]> circulation = new ArrayList<String[]>();
		ArrayList<String[]> computerSessions = new ArrayList<String[]>();
		ArrayList<String[]> visitors = new ArrayList<String[]>();
		ArrayList<String[]> wifiUse = new ArrayList<String[]>();
		readLibraryCSV obj = new readLibraryCSV();
		
		obj.run("Libraries_-_2014_Circulation_by_Location.csv", circulation);
		obj.run("Libraries_-_2014_Computer_Sessions_by_Location.csv", computerSessions);
		obj.run("Libraries_-_2014_Visitors_by_Location.csv", visitors);
		obj.run("Libraries_-_WiFi_Usage__2011-2014_.csv", wifiUse);
		}
	
	public void run(String resource, ArrayList<String[]> csv) {
		InputStream source = getClass().getClassLoader().getResourceAsStream(resource);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new InputStreamReader(source));
			while ((line = br.readLine()) != null) {
		        // use comma as separator
			String [] location = line.split(cvsSplitBy);
			csv.add(location);
				
			}
			for(String[] k: csv){
				System.out.println(Arrays.toString(k));
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
	//get the total number of computer sessions in Chicago
	public Integer getAllSessions(){
		int sessions = 0;
		//need to retrieve each YTD of each location then convert it to int, then add it;
		for(int i = 0; i<computerSessions.length;i++){
			//
			i+= ;
		}
		return sessions;
	}
	
	//get the total number of circulation (book rentals, renewals, etc)
	public Integer getAllCirculation(){
		int circulation = 0;
		
		return circulation;
	}
	
	//get the total number of visitors in Chicago
	public Integer getAllVisitors(){
		int sessions = 0;
		
		return sessions;
	}
//funciton compare visitors to computer sessions
//function compare visitors to circulation
//function compare visitors ot wifi usage
	public void compareVisitors(){
		
	}

//function(s) that finds most popular location (for each thing)

	
//compare above outcomes to eachother and then decide what is most and/or least used, also when and where
	public void getPopularService(){
		
	}
}