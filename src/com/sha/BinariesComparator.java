package com.sha;

import java.io.*;
import java.util.*;
import com.sha.FileScanner;
import com.sha.FileReader;
import com.sha.Difference;
import java.text.SimpleDateFormat;

public class BinariesComparator {
	private List<Difference> _diffs;
	private String _binaryLog;
	private List<String> _directories;
	private final String ADDED = "Added";
	private final String MODIFIED = "Modified";
	private final String DELETED = "Deleted";
	
	public BinariesComparator(String strBinaryLog, List<String> directories) {
		_binaryLog = strBinaryLog;
		_directories = directories;
		_diffs = new ArrayList<Difference>();
	}
	
	public List<Difference> getDiffs(){ return _diffs; }
	
	public void tellTheDiffs() {
		File tmpFile = null;
		Map<String, String> readerMap = FileReader.getFileReader().ReadBinLog(_binaryLog);
		Map<String, String> scannerMap = FileScanner.getFileScanner().getDirsSHA512(_directories);	// directories configured
		SimpleDateFormat lastModifiedDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(Map.Entry<String, String> tmpScannerMap : scannerMap.entrySet()) {
			if(!readerMap.containsKey(tmpScannerMap.getKey())) {
				// item added
				tmpFile = new File(tmpScannerMap.getKey());
				_diffs.add(new Difference(tmpScannerMap.getKey(), lastModifiedDateTime.format(
						new Date(tmpFile.lastModified())), tmpFile.length(), ADDED));
			}else {
				if(!(tmpScannerMap.getValue().toString().equals(readerMap.get(tmpScannerMap.getKey()).toString()))) {
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
				_diffs.add(new Difference(tmpReaderMap.getKey(), "", tmpFile.length(), DELETED));
			}
		}
	}
}
