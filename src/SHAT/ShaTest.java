package SHAT;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
//import java.util.*;

public class ShaTest {

	public static String encrypt(File myFile, String encMethod) {
		String strDesc = null;
		MessageDigest md = null;
		InputStream input = null;
		
		try {
			input = new FileInputStream(myFile);
			byte[] bt = new byte[input.available()];
			if(encMethod == null || "".equals(encMethod)) {
				encMethod = "SHA-256";
			}
			md = MessageDigest.getInstance(encMethod);
			md.update(bt);
			strDesc = bytes2Hex(md.digest());
		}
		catch(NoSuchAlgorithmException ex) {
			return null;
		}
		catch(Exception e) {
			return null;
		}
		return strDesc;
	}
	
	public static String bytes2Hex(byte[] bts) {
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
	  
	public static void main(String args[]) {
		File file = new File("H:\\fixshell.exe");
		String s = ShaTest.encrypt(file, "SHA-512");
		System.out.println("Output is ");
		System.out.println(s);
		//HashMap<String , Double> map = new HashMap<String , Double>(); 
	}
	
	
}
