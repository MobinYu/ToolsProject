package com.study.hash;

public class HashTest {

	public static void main(String[] args) {
		
		/* hashCode()值不是很散列 */
		
		System.out.println("hashCode():");
		System.out.println("192.168.253.0:8080的哈希值： " + "192.168.253.0:8080".hashCode());
		System.out.println("192.168.253.1:8080的哈希值： " + "192.168.253.1:8080".hashCode());
		System.out.println("192.168.253.2:8080的哈希值： " + "192.168.253.2:8080".hashCode());
		System.out.println("192.168.253.3:8080的哈希值： " + "192.168.253.3:8080".hashCode());
		System.out.println("192.168.253.4:8080的哈希值： " + "192.168.253.4:8080".hashCode());
		
		System.out.println();
		System.out.println("FNV1_32_HASH:");
		System.out.println("192.168.253.0:8080的哈希值： " + FNV1_32_HASH.getHash("192.168.253.4:8080"));
		System.out.println("192.168.253.1:8080的哈希值： " + FNV1_32_HASH.getHash("192.168.253.1:8080"));
		System.out.println("192.168.253.2:8080的哈希值： " + FNV1_32_HASH.getHash("192.168.253.2:8080"));
		System.out.println("192.168.253.3:8080的哈希值： " + FNV1_32_HASH.getHash("192.168.253.3:8080"));
		System.out.println("192.168.253.4:8080的哈希值： " + FNV1_32_HASH.getHash("192.168.253.4:8080"));
	}
	
}
