package Scrapper;

import java.io.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrapper {

	public static void main (String[] args) throws IOException{
		MasScrape();
	}
	
	public static String[][] Scrape () throws IOException {
		String html = "https://www.worldometers.info/coronavirus/";
		Document doc = Jsoup.connect(html).get();
		
		Elements paragraphs = doc.select("td");
		ArrayList arr = new ArrayList();
		String [][] CV = new String[28][8];
		int i = 0;
        for (Element p : paragraphs) {
        	if (p.ownText().equals("")) {
        		CV[i/8][i%8] = "0";
        	}
        	else {
        		CV[i/8][i%8] = p.ownText();
        	}
        	i++;
        }
        
        return CV;
		
		//System.out.println(doc);
	}

	public static String MasScrape() throws IOException{
		String html = "https://www.worldometers.info/coronavirus/";
		Document doc = Jsoup.connect(html).get();
		String Marcus = doc.title();

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();

		for(int i = 0; i < Marcus.length()/2 + 2; i++){
			sb.append(Marcus.charAt(i));
		}
		String first = sb.toString();

		for(int i = Marcus.length()/2 + 2; i < Marcus.length(); i++){
			sb1.append(Marcus.charAt(i));
		}
		String sec = sb1.toString();

		String fin = first + "\n" + sec;
		return fin;
	}
	
	public static int ConvertStringToInt (String num) {
		// removes +
		if (num.charAt(0) == '+')
			num = num.substring(1);
		
		// removes commas
		String newString = num.replace(",", "");
		
		int i = Integer.parseInt(newString);
	
		return i;
	}
}
