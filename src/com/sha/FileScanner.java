package com.sha;

import java.io.File;
import java.util.*;
import com.sha.SHAGenerator;

public class FileScanner {
	private static HashMap<String, String> _binariesMap = new HashMap<String, String>();
	private static SHAGenerator _shaGenerator = new SHAGenerator();
	private static final FileScanner FILE_SCANNER= new FileScanner();
	
	private FileScanner() {}
	// each directory which would contain sub-folders
	private void getBinariesSHA512(File[] myFiles){
		for(File tmpFile : myFiles) {
			if(tmpFile.isFile()) {
				_binariesMap.put(tmpFile.getAbsolutePath(), _shaGenerator.GenSHA(tmpFile));
			}else {
				// is a directory
				for(File tmpSubFile : tmpFile.listFiles()) {
					_binariesMap.put(tmpSubFile.getAbsolutePath(), _shaGenerator.GenSHA(tmpSubFile));
				}
			}
		}
	}
	
	// scan directories configured. 
	public  HashMap<String, String> getDirsSHA512(List<String> dirPaths){
		for(int i = 0; i < dirPaths.size(); i++) {
			getBinariesSHA512(new File(dirPaths.get(i)).listFiles());
		}
		
		/*
		// sort in aes
		List<Map.Entry<String, String>> tmpList = new ArrayList<Map.Entry<String, String>>
						(_binariesMap.entrySet());
		Collections.sort(tmpList, new Comparator<Map.Entry<String, String>>(){
				public int compare(Map.Entry<String, String> lMap, Map.Entry<String, String> hMap) {
					return (lMap.getKey().toString().compareTo(hMap.getKey()));
				}
		});
		*/
		return _binariesMap;
	}
	
	public Map<String, String> getBinariesMap(){
		return _binariesMap;
	}
	
	public static FileScanner getFileScanner() {
		return FILE_SCANNER;
	}
	

}
