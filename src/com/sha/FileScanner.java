package com.sha;

import java.io.*;
import java.util.*;

public class FileScanner {
	private String _DirectoryPath;
	
	private List<String> getAllFilesPaths(File[] myFiles) {
		List<String> filePaths = new ArrayList<String>();
		
		for(File tmpFile: myFiles) {
			if(tmpFile.isFile()) {
				filePaths.add(tmpFile.getAbsolutePath());
			}else {
				// is a directory
				for(File tmpSubFile: tmpFile.listFiles()) {
					filePaths.add(tmpSubFile.getAbsolutePath());
				}
			}
		}
		return filePaths;
	}
	
	public FileScanner(String strDirectoryPath) {
		_DirectoryPath = strDirectoryPath;
	}
	public void setDirectoryPath(String strDirectoryPath) {
		_DirectoryPath = strDirectoryPath;
	}
	
	public List<String> getFilePaths(){
		List<String> filePaths = null;
		
		File dir = new File(_DirectoryPath);
		if(!dir.isDirectory()) {
			return null;
		}else {
			// Get files' paths and sub-folders 
			filePaths = getAllFilesPaths(dir.listFiles());
		}
		return filePaths;
	}
}
