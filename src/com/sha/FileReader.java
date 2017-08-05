package com.sha;

import java.io.*;
import java.util.*;

public class FileReader {
	private static String _binLog;
	private FileReader() {}
	private static final FileReader FILE_READER = new FileReader();
	
	/*
	private void BackupBinLog() {		
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel inCha = null;
		FileChannel outCha = null;
		
		try {
			fi = new FileInputStream(new File(_binLog));
			fo = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\logs\binariesLog_" + 
						new SimpleDateFormat("yyyyMMddHHmm").toString() + ".log" )); // backup path
			
			inCha = fi.getChannel();
			outCha = fo.getChannel();
			inCha.transferTo(0, inCha.size(), outCha);	// copy file
		}catch(IOException ioex) {
			ioex.printStackTrace();
		}finally{
			try {
			fi.close();
			fo.close();
			inCha.close();
			outCha.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	*/
	
	public static FileReader getFileReader() {
		return FILE_READER;
	}

	public HashMap<String, String> ReadBinLog(String strBinLog){
		_binLog = strBinLog;
		BufferedReader reader = null;
		String tmpString = null;
		String[] tmpStrings  = null;
		FileInputStream fips = null;
		InputStreamReader ipsr = null;
		HashMap<String, String> binHashMap = null;
				
		try {
			fips = new FileInputStream (new File(_binLog));
			ipsr = new InputStreamReader(fips);
			reader = new BufferedReader(ipsr);
			binHashMap = new HashMap<String, String>();
			
			while((tmpString = reader.readLine())!= null) {
				tmpStrings = tmpString.split("\\|");
				binHashMap.put(tmpStrings[0], tmpStrings[1]);
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				reader.close();
				ipsr.close();
				fips.close();
			}catch(IOException e) {
				e.printStackTrace();
			} 
		}
		return binHashMap;
	}
}
