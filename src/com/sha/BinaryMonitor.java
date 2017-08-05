package com.sha;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class BinaryMonitor {
	
	public static void main(String args[]) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Program Started: " + df.format(new Date()));
		
		// the directories of the binaries
		List<String> directories = new ArrayList<String>();
		directories.add("C:\\Users\\Administrator\\Desktop\\test folder");	// read the configuration file on production
		
		final String BINARIES_LOG = "C:\\Users\\Administrator\\Desktop\\logs\\binariesLog.log";
		ResultGenerator resultGenerator = null;
		List<Difference> differences;
		
		//check if the log of binaries exists
		File logFile = new File(BINARIES_LOG);
		if(!logFile.exists()) {
			System.out.println("Day one");
			//Generate the binaries log
			resultGenerator = new ResultGenerator(FileScanner.getFileScanner().getDirsSHA512(directories));
			resultGenerator.generateResultLog(BINARIES_LOG);
		}else {
			BinariesComparator binariesComparator = new BinariesComparator(BINARIES_LOG, directories);
			binariesComparator.tellTheDiffs();
			differences = binariesComparator.getDiffs();
			resultGenerator = new ResultGenerator(FileScanner.getFileScanner().getBinariesMap(), differences);
			
			if(differences.size() == 0) {
				System.out.println("Binaries not modified.");
			}else {
				resultGenerator.generateDifference();
			}
			//Backup the old binaries log and generate a new one
			resultGenerator.backupBinLog(BINARIES_LOG);
			resultGenerator.generateResultLog(BINARIES_LOG);
		}
		System.out.println("Program Ended: " + df.format(new Date()));
	}

}
