package com.sha;

import java.util.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

public class ResultGenerator {
	private Map<String, String> _binariesMap;
	private List<Difference> _diffs;
	
	public ResultGenerator(Map<String, String> binariesMap, List<Difference> diffs) {
		_binariesMap = binariesMap;
		_diffs = diffs;
	}
	
	public ResultGenerator(Map<String, String> binariesMap) {
		_binariesMap = binariesMap;
	}
	
	public ResultGenerator(List<Difference> diffs) {
		_diffs = diffs;
	}
		
	/*
	 * file format
	 * absolute path | modified datetime | binary size in byte | status(added, modified, deleted)
	 */
	public void generateDifference() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\backup\\diff_" + 
						new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".log");
		
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
						" | " + tmpDiff.getSize() + " bytes " + " | " + tmpDiff.getStatus());
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
		Iterator it = _binariesMap.entrySet().iterator();
		Map.Entry<String, String> entry = null;
		
		if(file.exists()) 
			file.delete();
		
		try {
			file.createNewFile();
			
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			
			while(it.hasNext()) {
				entry = (Map.Entry<String, String>) it.next();
				writer.write(entry.getKey().toString() + "|" + entry.getValue().toString());
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
	
	public void backupBinLog(String strBinPath) {		
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel inCha = null;
		FileChannel outCha = null;
		File backupFile = null;
		
		try {
			backupFile = new File("C:\\Users\\Administrator\\Desktop\\logs\\binariesLog_" + 
					new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".log");
			
			if(backupFile.exists()) { backupFile.delete(); }
			backupFile.createNewFile();
			
			fi = new FileInputStream(new File(strBinPath));
			fo = new FileOutputStream(backupFile); // backup path
			
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
}
