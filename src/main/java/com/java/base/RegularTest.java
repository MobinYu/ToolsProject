package com.java.base;

import java.util.regex.Pattern;

public class RegularTest {
	
	public static void main(String[] args) {
		
		String str = "ABC";
		
		System.out.println(str.charAt(0));
		
		StringBuffer sb = new StringBuffer("ABCDEFG");
		
		System.out.println(sb.reverse());
		System.out.println(sb.replace(1, 3, "M")); //1-位字符整个被M取代
		
		
		String s1 = "12DDs";
		String part1 = "\\d.*([A-Z]|[a-z])$"; //以数字开头，字母结尾
		System.out.println(Pattern.matches(part1, s1));
		
		String s2 = "vABc7845434";
		String part2 = "^([A-Z]|[a-z]).*[\\d]$"; //以字母开头，数字结尾
		System.out.println(Pattern.matches(part2, s2));
		
		String regex = ".*[a-zA-Z]+.*"; //是否包含英文字母
		System.out.println(Pattern.matches(regex, "尽快到静安寺靠大家分卡萨丁123"));
	}

}
