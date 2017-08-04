package com.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class SHAGenerator {
	private  MessageDigest _md;
	private  final String SHA = "SHA-512";
	
	public SHAGenerator() {}
	
	// Binary to Hex
	private String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		
		for(int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	
	public String GenSHA(File myFile) {
		String strHex = "";
		FileInputStream fips = null;
		
		try {
			fips = new FileInputStream(myFile);
			byte[] bt = new byte[fips.available()];	// Assign the size 
			_md = MessageDigest.getInstance(SHA);
			_md.update(bt);
			strHex = bytes2Hex(_md.digest());
			
		}catch(NoSuchAlgorithmException aex) {
			System.out.println(aex.getStackTrace());
		}catch(Exception ex) {
			System.out.println(ex.getStackTrace());
		}finally {
			try {
				fips.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		return strHex;
	}
}
