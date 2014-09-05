package cn.perfect.nio.base;

import java.nio.charset.Charset;

import org.apache.commons.lang.ArrayUtils;

public class ByteUtil {

	public static String ASCIICHARSET = "US-ASCII";
	
	 public static byte[] getBits(byte b) {  
	        byte[] array = new byte[8];  
	        for (int i = 7; i >= 0; i--) {  
	            array[i] = (byte)(b & 1);  
	            b = (byte) (b >> 1);  
	        }  
	        return array;  
	    }  
	 
    /** 
     * 把byte转为字符串的bit 
     */  
    public static String byteToBitString(byte b) {  
        return ""  
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
    }  

	public static byte[] getBytes(short data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) ((data & 0xff00) >> 8);
		bytes[1] = (byte) (data & 0xff);
		return bytes;
	}

	public static byte[] getBytes(char data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data >> 8);
		bytes[1] = (byte) (data);
		return bytes;
	}

	public static byte[] getBytes(int data) {
		byte[] bytes = new byte[4];
		bytes[3] = (byte) (data & 0xff);
		bytes[2] = (byte) ((data & 0xff00) >> 8);
		bytes[1] = (byte) ((data & 0xff0000) >> 16);
		bytes[0] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}

	public static byte[] getBytes(long data) {
		byte[] bytes = new byte[8];
		bytes[7] = (byte) (data & 0xff);
		bytes[6] = (byte) ((data >> 8) & 0xff);
		bytes[5] = (byte) ((data >> 16) & 0xff);
		bytes[4] = (byte) ((data >> 24) & 0xff);
		bytes[3] = (byte) ((data >> 32) & 0xff);
		bytes[2] = (byte) ((data >> 40) & 0xff);
		bytes[1] = (byte) ((data >> 48) & 0xff);
		bytes[0] = (byte) ((data >> 56) & 0xff);
		return bytes;
	}

	public static byte[] getBytes(float data) {
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
	}

	public static byte[] getBytes(double data) {
		long intBits = Double.doubleToLongBits(data);
		return getBytes(intBits);
	}

	public static short getShort(byte... bytes) {
		if(bytes.length <2) {
			bytes = ArrayUtils.addAll(new byte[2 - bytes.length], bytes);
		}
		
		return (short) ((0xff & bytes[1]) | (0xff00 & (bytes[0] << 8)));
	}

	public static char getChar(byte[] bytes) {
		return (char) ((0xff & bytes[1]) | (0xff00 & (bytes[0] << 8)));
	}

	public static int getInt(byte... bytes) {
		if(bytes.length <4) {
			bytes = ArrayUtils.addAll(new byte[4 - bytes.length], bytes);
		}
		return (0xff & bytes[3]) | (0xff00 & (bytes[2] << 8))
				| (0xff0000 & (bytes[1] << 16))
				| (0xff000000 & (bytes[0] << 24));
	}

	public static long getLong(byte... bytes) {
		if(bytes.length <8) {
			bytes = ArrayUtils.addAll(new byte[8 - bytes.length], bytes);
		}
		
		return (0xffL & (long) bytes[7]) | (0xff00L & ((long) bytes[6] << 8))
				| (0xff0000L & ((long) bytes[5] << 16))
				| (0xff000000L & ((long) bytes[4] << 24))
				| (0xff00000000L & ((long) bytes[3] << 32))
				| (0xff0000000000L & ((long) bytes[2] << 40))
				| (0xff000000000000L & ((long) bytes[1] << 48))
				| (0xff00000000000000L & ((long) bytes[0] << 56));
	}

	public static float getFloat(byte... bytes) {
		return Float.intBitsToFloat(getInt(bytes));
	}

	public static double getDouble(byte[] bytes) {
		long l = getLong(bytes);
		return Double.longBitsToDouble(l);
	}

	public static String getString(byte[] bytes, String charsetName) {
		return new String(bytes, Charset.forName(charsetName));
	}

	public static String getString(byte[] bytes) {
		return getString(bytes, "utf-8");
	}

	public static byte[] getBytes(String data, String charsetName) {
		Charset charset = Charset.forName(charsetName);
		return data.getBytes(charset);
	}

	public static byte[] getBytes(String data) {
		return getBytes(data, "utf-8");
	}

	public static void main(String[] args) {
		/*
		 * for (byte b : getBytes("123", "US-ASCII")) { System.out.println(b); }
		 * System.out.println(getString(getBytes("123", "US-ASCII"),
		 * "US-ASCII"));
		 * 
		 * byte[] t = new byte[]{-1};
		 * System.out.println(getString(t,"US-ASCII")); char a = 49;
		 * System.out.println(a);
		 */
		
		
		for(byte a : getBits((byte) 3)) {
			System.out.print(a==1);
			System.out.print(a+",");
		}
		System.out.println();
		System.out.println(getShort(new byte[]{(byte) 0x90,0x11}));
		
		
		
		System.out.println(getInt(new byte[]{1,2}));
		System.out.println(getLong(new byte[]{1,2}));
		System.out.println(getShort(new byte[]{1,2}));
		System.out.println(getShort(new byte[]{2}));
		
		for (byte a : ArrayUtils.addAll(new byte[2], new byte[]{1,2})) {
			System.out.println(a);
		}
	}
}
