package com.sha;

import java.util.*;
import com.sha.Difference;
import java.io.*;
import java.text.SimpleDateFormat;

public class ResultGenerator {
	private HashMap<String, String> _hashMap;
	private List<Difference> _diffs;
	
	public ResultGenerator(HashMap<String, String> hashMap) {
		_hashMap = hashMap;
	}
	
	public ResultGenerator(List<Difference> diffs) {
		_diffs = diffs;
	}
	
	
	/*
	 * file format
	 * absolute path | modified datetime | binary size in byte | status(added, modified, deleted)
	 */
	public void generateDifference() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\backup\\diff" + 
						new SimpleDateFormat("yyyyMMddHHmm").toString() + ".log");
		FileWriter fw = null;
		BufferedWriter writer = null;
		Iterator<Difference> it = _diffs.iterator();
		Difference tmpDiff = null;
		
		if(file.exists()) 
			file.delete();
		
		try {
			file.createNewFile();
			
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			
			while(it.hasNext()) {
				tmpDiff = it.next();
				writer.write(tmpDiff.getBinPath() + " | " + tmpDiff.getModifiedDateTime() + 
						" | " + tmpDiff.getSize() + " | " + tmpDiff.getStatus());
				writer.newLine();
			}
			writer.flush();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}finally {
			try {
				writer.close();
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*
	 * file format
	 * absolute path | SHA-512 code
	 */
	public void generateResultLog(String strBinPath) {
		File file = new File(strBinPath);
		FileWriter fw = null;
		BufferedWriter writer = null;
		Iterator it = _hashMap.entrySet().iterator();
		Map.Entry<String, String> entry = null;
		
		if(file.exists()) 
			file.delete();
		
		try {
			file.createNewFile();
			
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			
			while(it.hasNext()) {
				entry = (Map.Entry<String, String>) it.next();
				writer.write(entry.getKey().toString() + " | " + entry.getValue().toString());
			}
			writer.flush();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}finally {
			try {
				writer.close();
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
