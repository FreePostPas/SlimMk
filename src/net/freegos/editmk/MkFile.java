package net.freegos.editmk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MkFile {
	private String path, fileName;
	private File file;
	private FileOutputStream outputFile = null;
	private FileInputStream inputFile = null;
	
	public MkFile(String path, String fileName) {
		this.path = path;
		this.fileName = fileName;
		
		file = new File(path);
		try {
			outputFile = new FileOutputStream(file);
			inputFile = new FileInputStream(file);
			
			byte[] b = null;
			System.out.println(inputFile.read(b));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
	            if (inputFile != null) {
	               inputFile.close();
	            }
	         } catch (IOException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (outputFile != null)
	               outputFile.close();
	         } catch (IOException e) {

	            e.printStackTrace();

	         }
		}
	}
	
	public String path() {
		return this.path;
	}
	
	public void path(String path) {
		this.path = path;
	}
	
}
