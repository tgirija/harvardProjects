package cscie599.asn1.tickexample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TickCreator {

	public static void main(String[] args) {
		if(args[0]!=null){
			importFile(args[0]);
			///hmmm			
			//generateTicks(min, max);
			
		}
	}
	
	
	
	public static void generateTicks(String min, String max){
		System.err.println(min +";" +max);
		
	}
	
	public static void importFile(String fileName){
		BufferedReader br = null;
		try {
			
			FileInputStream fstream = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            String[] splittedLine = new String[3];
            
            //read file line by line
            while ((strLine = br.readLine()) != null) {
             	            	
            	if(!strLine.equals("")){
            		splittedLine = strLine.trim().split("\\s+");
            		generateTicks(splittedLine[0], splittedLine[1]);
            		//return splittedLine;
            	}
            }
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//return null;
	}
}
