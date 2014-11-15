package net.freegos.editmk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MkFile {
	private String path, fileName;
	private File file;
	private FileInputStream inputFile = null;
	private FileOutputStream outputFile = null;
	
	private String tempContent;
	
	public MkFile(String path) {
		this.path = path;
		String[] partPath = path.split("\\\\");		
		this.fileName = partPath[partPath.length - 1];
		
		file = new File(path);
		try {
			inputFile = new FileInputStream(file);
			outputFile = new FileOutputStream(file);
			
			tempContent = "";
			for( int a = inputFile.read(); a!=-1; a = inputFile.read()) {
		         tempContent += (char)a;				
			}
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
		}
		
	}
	
	public MkFile() {
		this.path = null;
		try {
			file = File.createTempFile("Unsaved.mk", ".tmp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			inputFile = new FileInputStream(file);
			
			tempContent = "";
			for( int a = inputFile.read(); a!=-1; a = inputFile.read()) {
		         tempContent += (char)a;				
			}
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
		}
	}

	public String path() {
		return this.path;
	}
	
	public void path(String path) {
		this.path = path;
	}
	
	public String fileName() {
		return this.fileName;
	}

	public String content()  {
		return this.tempContent;
	}

	public void content(String content)  {
		this.tempContent = content;
		updateFile();
	}
	
	public void updateFile() {
		try {
			outputFile.write(tempContent.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
