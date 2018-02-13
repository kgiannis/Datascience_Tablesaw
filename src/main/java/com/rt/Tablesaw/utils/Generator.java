package com.rt.Tablesaw.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Generator {

	private static int getRandomNumber(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static java.sql.Date getRandomDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, getRandomNumber(0,7));
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
	public static int getRandomApproval(){
		return getRandomNumber(10,80);
	}
	
	public static String getRandomWho(){
		String[] names = {"giannis", "vasilis", "akis", "nikos", "kostas"};
		return names[getRandomNumber(0,4)];
	}
	
	public static String formatElapsedTime(long start, long end){
		long millis = end - start;
		
		int seconds = (int) ((millis / 1000) % 60);
		int minutes = (int) ((millis / 1000) / 60);
		return String.format("%d min, %d sec", minutes, seconds);
	}
	
	public static List<String> getRandomWhos(int size){
		List<String> whos = new ArrayList<String>();
		for(int counter=0 ; counter<size ; counter++){
			whos.add( getRandomWho() );
		}
		return whos;
	}
}
