package br.gcc.ppoo.ArquivoBin;

// ass package br.gcc.ppoo

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logins {

	public static void main(String[] args) {
		
		File fileLogin = new File("fileLogin.bin");		
//		fileLogin.mkdir();
//		Scanner in = new Scanner(System.in);
		
		if (! fileLogin.exists()) {
			System.out.println("Nao existe");
			try {
				fileLogin.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (fileLogin.exists()) {
			System.out.println("File Existe");
			try {
				FileWriter fileWriter = new FileWriter(fileLogin, true);
				BufferedWriter buffeWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < 10; i++) {
					buffeWriter.write("Hemerson" + i);
					buffeWriter.newLine();
				}
				
				buffeWriter.close();
				fileWriter.close();
				
				FileReader fileread = new FileReader(fileLogin);
				@SuppressWarnings("resource")
				BufferedReader filereader = new BufferedReader(fileread);
				String linha = filereader.readLine();	
				
				while (linha != null) {
					System.out.println(linha);
					linha = filereader.readLine();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}	
	}
} 
