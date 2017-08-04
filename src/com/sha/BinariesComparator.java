package com.sha;

import java.io.*;
import java.util.*;
import com.sha.FileScanner;
import com.sha.FileReader;
import com.sha.Difference;
import java.text.SimpleDateFormat;

public class BinariesComparator {
	private List<Difference> _diffs;
	private final String ADDED = "Added";
	private final String MODIFIED = "Modified";
	private final String DELETED = "Deleted";
	
	public BinariesComparator() {
		_diffs = new ArrayList<Difference>();
	}
	
	public List<Difference> getDiffs(){ return _diffs; }
	
	public void tellTheDiffs(FileReader fileReader, FileScanner fileScanner) {
		HashMap<String, String> readerMap = fileReader.getFileReader().ReadBinLog("BinLog");
		HashMap<String, String> scannerMap = fileScanner.getFileScanner().getDirsSHA512(new ArrayList<String>());	// directories configured
		File tmpFile = null;
		SimpleDateFormat lastModifiedDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(Map.Entry<String, String> tmpScannerMap : scannerMap.entrySet()) {
			if(!readerMap.containsKey(tmpScannerMap.getKey())) {
				// item added
				tmpFile = new File(tmpScannerMap.getKey());
				_diffs.add(new Difference(tmpScannerMap.getKey(), lastModifiedDateTime.format(
						new Date(tmpFile.lastModified())), tmpFile.length(), ADDED));
			}else {
				if(!readerMap.containsValue(tmpScannerMap.getValue())){
					// item modified
					tmpFile = new File(tmpScannerMap.getKey());
					_diffs.add(new Difference(tmpScannerMap.getKey(), lastModifiedDateTime.format(
							new Date(tmpFile.lastModified())), tmpFile.length(), MODIFIED));
				}
			}			
		}
		
		for(Map.Entry<String, String> tmpReaderMap : readerMap.entrySet()) {
			if(!scannerMap.containsKey(tmpReaderMap.getKey())){
				// item deleted
				tmpFile = new File(tmpReaderMap.getKey());
				_diffs.add(new Difference(tmpReaderMap.getKey(), lastModifiedDateTime.format(
						new Date(tmpFile.lastModified())), tmpFile.length(), DELETED));
			}
		}
	}
}
