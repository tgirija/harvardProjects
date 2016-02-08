package cscie599.asn1.tickexample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TickCreator {

	public static double maxTicks = 8;
		
	/**
	 * Method which calls the NiceScaleAlgorithm and generate the ticks, 
	 * concatenates the ticks into a single string separated by space
	 * @param min
	 * @param max
	 * @return
	 */
	public static String generateTicks(double min, double max){
		System.err.println(min+":"+max);
		
		NiceScale numScale = new NiceScale(min, max);
		double range = numScale.getRange();
		double interval = numScale.getTickInterval(range, maxTicks);
		double niceMin =  numScale.getNiceMin(interval);
		double niceMax =  numScale.getNiceMax(interval);
		
		/*System.out.println("Min:\t" + min);
		System.out.println("Max:\t" + max);
		System.out.println("interval:\t" + interval);
		System.out.println("Nice Minimum:\t" + numScale.getNiceMin(interval));
		System.out.println("Nice Maximum:\t" + numScale.getNiceMax(interval));*/

		
		double tick = niceMin;
		StringBuffer buff = new StringBuffer();
		buff.append(niceMin);

		while(tick+interval < niceMax){
			tick = tick+interval;
			buff.append(" ");
			buff.append(roundNum(tick));
		}
		
		if(roundNum(tick)!=niceMax){//do not add the nicemax if the last tick has already reached nicemax
			buff.append(" "+niceMax);
		}
		System.out.println(buff.toString());
		return buff.toString();
	}
	
	
	/**
	 * Method which writes the generated String of ticks into an output file
	 * @param tickLines
	 */
	public static void writeToFile(List<String> tickLines, String outputFileName) {
		
		PrintWriter writer=null;
		
		if(tickLines!=null && outputFileName!=null){
			try {
				writer = new PrintWriter(outputFileName, "UTF-8");
				for(String line: tickLines){
					writer.println(line);
				}
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *Method which reads the input file line by line and call the generateTicks method for each input line
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<String> importFileAndGenerateTicks(String fileName) throws IOException{
		BufferedReader br = null;
		
		if(fileName!=null){
			try {
				
				FileInputStream fstream = new FileInputStream(fileName);
				br = new BufferedReader(new InputStreamReader(fstream));
	            String strLine;
	            
	            //array of min and max values
	            String[] splittedLine = new String[2];
	            String ticks = null;
	            List<String> tickList = new ArrayList<>();
	            //read file line by line
	            while ((strLine = br.readLine()) != null) {
	             	            	
	            	if(!strLine.equals("")){
	            		//split the line by tab into an array of min and max values
	            		splittedLine = strLine.trim().split("\\s+");
	            		ticks = generateTicks(Double.parseDouble(splittedLine[0]), Double.parseDouble(splittedLine[1]));
	            		tickList.add(ticks);
	            	}
	            }
	            return tickList;   
	        }
			catch (IOException e) {
				throw e;
			}
			finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		if(args[0]!=null){
			try {
				List<String> tickList = importFileAndGenerateTicks(args[0]);
				writeToFile(tickList, "thankam_girija.txt");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Uncomment this for randomization tests. The methods generateRandomMin() and generateRandomMax() 
		//generates a random double value for min and max which can be given as input to the tickgenerator method
		//generateTicks(generateRandomMin(), generateRandomMax());
	}
	
	
	/**
	 * Helper method to round a double num to 2 decimal places.
	 * @param num
	 * @return
	 */
	private static double roundNum(double num){
		return (double) Math.round(num * 100) / 100;
	}
	
	/**
	 * Method which generates a random double value between 1 and 5, this num can be used as the min input for TickCreator
	 */
	private static double generateRandomMin(){
		
		Random r = new Random();
		double randomValue = 1 + (5 - 1) * r.nextDouble();
		return roundNum(randomValue);
	}
	
	/**
	 * Method which generates a random double value between 5 and 10, this num can be used as the max input for TickCreator
	 */
	private static double generateRandomMax(){
		
		Random r = new Random();
		double randomValue = 5 + (10 - 5) * r.nextDouble();
		return roundNum(randomValue);
	}
}

