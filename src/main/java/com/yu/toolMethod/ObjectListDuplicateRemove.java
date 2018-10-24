package com.yu.toolMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.yu.model.User;

/**
 * 对象集合去重
 * 
 * @author Administrator
 *
 */
public class ObjectListDuplicateRemove {
	
	public static List<User> removeDuplicateObject1(List<User> userlist) {
		for (int i = 0; i < userlist.size()-1; i++) {
			for (int j = userlist.size()-1; j > i; j--) {
				if (userlist.get(i).getName().equals(userlist.get(j).getName())){
					userlist.remove(j);
				}
			}
		}
		return userlist;
	}

	public static List<User> removeDuplicateObject2(List<User> userlist) {
		Set<User> set = new TreeSet<User>(new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		set.addAll(userlist);
		return new ArrayList<User>(set);
	}
}
