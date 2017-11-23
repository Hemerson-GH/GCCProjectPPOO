package br.ufla.gcc.ppoo.SeparaString;

import java.util.ArrayList;
import java.util.Scanner;

public class SeparaString {
	
	public static ArrayList<String> SeparaStrings(String palavraInteira){
		String[] result = palavraInteira.split("-");
		
		ArrayList<String> list = new ArrayList<>();
		
		for (String string : result) {
			list.add(string);
		}
		
		return list;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
//	    String string1= "Hemerson-Batista-Filho-DC-MARVEL-Heroes";
//	    String[] result = string1.split("\\.");
//	    String[] result = string1.split("\\.");
//	 
//	    You can also use Pattern.quote method if you find escaping escape characters
//	    String[] output = string1.split(Pattern.quote("."));
//	    System.out.println(result[0]);
//	    System.out.println(result[1]);
//	    System.out.println(result[2]);
//	    
//	    for (String string : result) {
//			System.out.println(string);
//		}
		
		Scanner in = new Scanner(System.in);
		
		String text = in.nextLine();
		
		ArrayList<String> list = SeparaStrings(text);
		
		for (String string : list) {
			System.out.println(string);
		}
	}
}
