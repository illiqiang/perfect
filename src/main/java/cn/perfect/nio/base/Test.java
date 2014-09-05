package cn.perfect.nio.base;

import java.io.IOException;


public class Test {
	public static void main(String[] args) throws IOException {
	/*	System.out.println(new Date(1407859200000l));
		InputStream in = new ByteArrayInputStream(new byte[]{1});
		
		
		int a = in.read();
		int b = in.read();
		System.out.println(a+"aa"+b);*/
		
		String a = new String("3242");
		String b = a;
		a = null;
		
		System.out.println(b==null);
		
	}
}
