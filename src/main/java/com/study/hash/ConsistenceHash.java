package com.study.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 应对缓存集群，增减服务器的问题
 * part3 --> 4、分布式一致性hash算法
 * 
 * @author Super Yu
 *
 */
public class ConsistenceHash {

	//物理节点
	List<String> realNodes = new ArrayList<String>();
	
	//物理节点与虚拟节点的对应关系,存储虚拟节点的hash值
	private Map<String, List<Integer>> real2VirtualMap = new HashMap<>();
	
	//红黑树，排序存储结构,key是虚拟节点的hash值，value是物理节点
	private SortedMap<Integer, String> sortedMap = new TreeMap<>();
	
	private int virtualNum = 100;

	public ConsistenceHash(int virtualNum) {
		super();
		this.virtualNum = virtualNum;
	}
	
	public ConsistenceHash() {
	}
	
	//添加服务器（节点）的方法
	public void addServer(String node){
		this.realNodes.add(node);
		
		//要添加的虚拟节点
		String vnode = null;
		int i = 0, count  = 0;
		
		List<Integer> virtualNodes = new ArrayList<>();
		this.real2VirtualMap.put(node, virtualNodes);
		
		//创建虚拟节点
		while (count < this.virtualNum) {
			vnode = node + "##" + i;
			int hashValue = FNV1_32_HASH.getHash(vnode);
			if (!this.sortedMap.containsKey(hashValue)){
				this.sortedMap.put(hashValue, node);
				virtualNodes.add(hashValue);
				count ++;
			}
			
			i ++;
		}
	}
	
	//减少服务器（节点）的方法
	public void removeServer(String node) {
		this.realNodes.remove(node);
		this.real2VirtualMap.remove(node);
		
		List<Integer> virtualNodes = this.real2VirtualMap.get(node);
		if (virtualNodes != null) {
			for (Integer vhash : virtualNodes) {
				this.sortedMap.remove(vhash);
			}
		}
	}
	
	//找到数据的存放节点（缓存的数据通过key找到对应的缓存服务器）
	public String getDataServer(String key) {
		int keyhash = FNV1_32_HASH.getHash(key);
		//找到所有大于keyhash值得所有虚拟节点map
		SortedMap<Integer, String> subMap = sortedMap.tailMap(keyhash);
		//subMap是null，说明要取得服务器节点，就是sortedMap中第一个key对应的值
		if (subMap != null) {
			//取第一个key
			Integer vhash = subMap.firstKey();
			//这个subMap中key对应的value，就是对应得服务器
			return subMap.get(vhash);
		} else {
			return this.sortedMap.get(sortedMap.firstKey());
		}
	}
	
	public static void main(String[] args) {
		ConsistenceHash ch = new ConsistenceHash();
		ch.addServer("192.168.253.10");
		ch.addServer("192.168.253.11");
		ch.addServer("192.168.253.12");
		
		//10调缓存数据
		for (int i = 0; i < 10; i++) {
			System.out.println("data" + i + " 对应服务器： " + ch.getDataServer("data" + i));
		}
	}
	
}
