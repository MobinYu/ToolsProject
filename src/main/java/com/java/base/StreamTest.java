package com.java.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StreamTest {

	public static void main(String[] args) throws IOException {
		/*
		 * InputStream 字节流
		 * InputStreamReader 字符流
		 * 
		 * 调用流的read()方法，会等待读取输入
		 * in.read()
		 * char str = (char) isr.read(); isr.read()转为char只会读取一个字符
		 * 
		 * InputStreamReader 是字节流通向字符流的桥梁，它使用指定的 charset 读取字节并将其解码为字符
		 */
		
		testStream1();
		
		testStream2();

		fileStream();
		
	}
	
	private static void testStream1 () throws IOException {
		InputStream in = System.in; //字节流
		InputStreamReader isr = new InputStreamReader(in); //字节流转为字符流
		
		char[] chs = new char[5];
		int len = isr.read(chs); //读取指定长度的字符，返回字符长度
		
		System.out.print("tream1：" + new String(chs));
		
		isr.close();
		in.close();
	}
	
	private static void testStream2 () throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("tream2：" + br.read()); //读取第一个字符，返回字符的character（0~65535）
		System.out.println("tream2：" + br.readLine()); //读取一行，返回字符串
	}
	
	private static void fileStream () {
		String path = "D:/java/file_test/test.txt";
		File file = new File(path);
		
		File fileParent = file.getParentFile();
		if (!fileParent.exists()){
			fileParent.mkdirs();
		}
		
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileInputStream fis = null;
		InputStreamReader in = null;
		BufferedReader bf = null;
		
		String newContent = "我是新写入的内容（写入时间：%s）";
		
		FileOutputStream fos = null;
		PrintWriter pw = null;
		
		try {
			fis = new FileInputStream(file);
			in = new InputStreamReader(fis);
			bf = new BufferedReader(in);
			
			StringBuffer writContent = new StringBuffer();
			
			String temp = "";
			//按行循环读取
			for (int i = 0; (temp = bf.readLine()) != null; i++) {
				writContent.append(temp);
				//换行符
				writContent.append(System.getProperty("line.separator"));
			}
			writContent.append(String.format(newContent, System.currentTimeMillis()));
			
			//写入
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(writContent.toString());
			
			pw.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null){
				pw.close();
			}
			if (fos != null){
				try {
					fos.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
			if (bf != null){
				try {
					bf.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
			if (in != null){
				try {
					in.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
			if (fis != null){
				try {
					fis.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
		}
		System.out.println("写入成功！");
	}
}
