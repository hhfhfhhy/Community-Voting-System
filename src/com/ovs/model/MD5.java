package com.ovs.model;

import java.security.MessageDigest;

public class MD5 {
	private MessageDigest md5;
	private static MD5 Instance;
	private String pageName = "MD5------------------------------------------------\n"; 

	/**
	 * Constructs the MD5 object and sets the string whose MD5 is to be
	 * computed.
	 * 
	 * @param inStr
	 *            the <code>String</code> whose MD5 is to be computed
	 */
	public synchronized static MD5 getMD5Instance() {
		if (Instance == null)
			Instance = new MD5();
		return Instance;
	}

	private MD5() {
		try {

			this.md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(pageName+"初始化MessageDigest类型的 MD5时抛出异常：      "+e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Computes the MD5 fingerprint of a string.
	 * 
	 * @return the MD5 digest of the input <code>String</code>
	 */
	public String compute(String inStr) {
		// convert input String to a char[]
		// convert that char[] to byte[]
		// get the md5 digest as byte[]
		// bit-wise AND that byte[] with 0xff
		// prepend "0" to the output StringBuffer to make sure that we don't end
		// up with
		// something like "e21ff" instead of "e201ff"

		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = this.md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}
	
	/**
	 * @author Better
	 * @param encodeStr
	 * @return
	 */
	public static String getMD5(String encodeStr) {
//		if(encodeStr == null){
//			return null;
//		}
		return MD5.getMD5Instance().compute(encodeStr);
	}

}
